/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.jms.ConnectionFactory;
import javax.sql.DataSource;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.processor.aggregate.jdbc.JdbcAggregationRepository;
import org.apache.camel.spi.ComponentCustomizer;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class CamelConfiguration extends RouteBuilder {

  private static final Logger log = LoggerFactory.getLogger(CamelConfiguration.class);

  @Autowired
  ApplicationContext applicationContext;
  
  @Bean
  ComponentCustomizer<SqlComponent> sqlComponentCustomizer(DataSource dataSource) {
    return (SqlComponent component) -> {
      component.setDataSource(dataSource);
    };
  }
  
  @Bean
  ComponentCustomizer<AMQPComponent> amqpComponentCustomizer(ConnectionFactory jmsConnectionFactory) {
    return (component) -> {
      component.setConnectionFactory(jmsConnectionFactory);
    };
  }
  
  @Bean
  @Scope("prototype")
  String uuid() {
    return UUID.randomUUID().toString();
  }
  
  @Bean
  JdbcAggregationRepository resultsAggregationRepository(DataSource dataSource, DataSourceTransactionManager dataSourceTransactionManager) {
    JdbcAggregationRepository jdbcAggregationRepository = new JdbcAggregationRepository();
    jdbcAggregationRepository.setDataSource(dataSource);
    jdbcAggregationRepository.setTransactionManager(dataSourceTransactionManager);
    jdbcAggregationRepository.setRepositoryName("SCORED_DATA_RESULTS_AGGREGATION");
    return jdbcAggregationRepository;
  }
  
  @Bean
  AggregationStrategy scoredDataResultsAggregationStrategy() {
    return (Exchange oldExchange, Exchange newExchange) -> {
      List<AccountHolder> list;
      if (oldExchange == null) {
        oldExchange = newExchange;
        list = new ArrayList<>();
      } else {
        list = oldExchange.getIn().getBody(List.class);
      }
      list.add(newExchange.getIn().getBody(AccountHolder.class));
      oldExchange.getIn().setBody(list);
      return oldExchange;
    };
  }
  
  @Bean
  JmsTransactionManager jmsTransactionManager(ConnectionFactory connectionFactory) {
    return new JmsTransactionManager(connectionFactory);
  }
  
  @Bean
  DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }
  
  @Bean
  SpringTransactionPolicy jmsTransactionPolicy(JmsTransactionManager jmsTransactionManager) {
    SpringTransactionPolicy policy = new SpringTransactionPolicy(jmsTransactionManager);
    policy.setPropagationBehaviorName("PROPAGATION_REQUIRED");
    return policy;
  }
  
  @Bean
  AggregationStrategy mismatchEnrichmentStrategy() {
    return (Exchange oldExchange, Exchange newExchange) -> {
      AccountHolder accountHolder = oldExchange.getIn().getBody(AccountHolder.class);
      List<String> codes = newExchange.getIn().getBody(List.class);
      boolean mismatch = true;
      if (codes.contains(accountHolder.getEphone()) ||
          codes.contains(accountHolder.getHphone())) {
        mismatch = false;
      }
      accountHolder.setMismatch((mismatch)?"Y":"N");
      return oldExchange;
    };
  }
  
  @Bean
  AggregationStrategy skipIdEnrichmentStrategy() {
    return (Exchange oldExchange, Exchange newExchange) -> {
      AccountHolder accountHolder = oldExchange.getIn().getBody(AccountHolder.class);
      Double randNum = newExchange.getIn().getBody(Double.class);
      accountHolder.setSkip_ID(randNum);
      return oldExchange;
    };
  }
  
  @Bean
  AggregationStrategy rulesEnrichmentStrategy() {
    return (Exchange oldExchange, Exchange newExchange) -> {
      AccountHolder accountHolder = oldExchange.getIn().getBody(AccountHolder.class);
      Map<String, Object> rulesResponse = newExchange.getIn().getBody(Map.class);
      if (!"SUCCESS".equalsIgnoreCase((String) rulesResponse.get("type"))) {
        accountHolder.setErrorMessage((String) rulesResponse.get("msg"));
      }
      Map<String, Object> result = (Map<String, Object>) rulesResponse.get("result");
      Map<String, Object> executionResults = (Map<String, Object>) result.get("execution-results");
      List<Map<String, Object>> resultsList = (List<Map<String, Object>>) executionResults.get("results");
      Map<String, Object> accountHolderResult = null;
      for (Map<String, Object> item : resultsList) {
        if ("account".equalsIgnoreCase((String) item.get("key"))) {
          accountHolderResult = (Map<String, Object>) ((Map<String, Object>) item.get("value")).get("com.toyota.tfs.decisionservice.AccountHolder");
        }
      }
      if (accountHolderResult != null) {
        accountHolder.setCurrentStage((String) accountHolderResult.get("currentStage"));
        accountHolder.setTreatmentOutcome((String) accountHolderResult.get("treatmentOutcome"));
        accountHolder.setLIST_1((String) accountHolderResult.get("list_1"));
      }
      return oldExchange;
    };
  }
  
  @Override
  public void configure() throws Exception {
    
    fromF("file:target/input/?delete=true&moveFailed=.failed")
      .log(LoggingLevel.DEBUG, log, "Processing file: [${header.CamelFileNameOnly}]")
      .unmarshal().beanio("/beanio-mappings.xml", "scoredDataFile")
      .setHeader("CorrelationID", simple("${ref:uuid}"))
      .transacted("jmsTransactionPolicy")
      .split(simple("${body[0]?.get('entries')}"))
        .log(LoggingLevel.DEBUG, log, "Processing entry: [${body.getID()}]")
        .setHeader("SplitIndex", exchangeProperty("CamelSplitIndex"))
        .setHeader("SplitSize", exchangeProperty("CamelSplitSize"))
        .marshal().json(JsonLibrary.Jackson)
        .to(ExchangePattern.InOnly, "amqp:queue:scored_data?acknowledgementModeName=SESSION_TRANSACTED&transacted=true&transactionManager=#jmsTransactionManager")
      .end()
    ;
    
    from("amqp:queue:scored_data?acknowledgementModeName=CLIENT_ACKNOWLEDGE&disableReplyTo=true")
      .unmarshal().json(JsonLibrary.Jackson, AccountHolder.class)
      .enrich()
        .constant("direct:callNpaSvc")
        .aggregationStrategyRef("mismatchEnrichmentStrategy")
      .end()
      /*
      .filter(simple("${body.getMismatch()} == 'Y'", Boolean.class))
        .transform().groovy("request.body.setErrorMessage(\"None of the area codes for account [${request.body.getID()}] match the state [${request.body.getSTATE()}].\"); return request.body;")
        .to("direct:aggregateToFile")
        .stop()
      .end()
      */
      .enrich()
        .constant("direct:callRandNumSvc")
        .aggregationStrategyRef("skipIdEnrichmentStrategy")
      .end()
      .enrich()
        .constant("direct:callRulesSvc")
        .aggregationStrategyRef("rulesEnrichmentStrategy")
      .end()
      .to("direct:aggregateToFile")
    ;
    
    from("direct:callNpaSvc")
      .setHeader(Exchange.HTTP_METHOD, constant("GET"))
      .setHeader("state", simple("${body.getSTATE()}"))
      .setBody(constant(null))
      .toD("http4://localhost:9090/camel/npa/by-state/${header.state}")
      .unmarshal().json(JsonLibrary.Jackson, Map.class)
      .transform().groovy("request.body['codes']")
    ;
    
    from("direct:callRandNumSvc")
      .setHeader(Exchange.HTTP_METHOD, constant("GET"))
      .setHeader("accountNum", simple("${body.getID()}"))
      .setBody(constant(null))
      .toD("http4://localhost:7070/camel/randnum/${header.accountNum}")
      .unmarshal().json(JsonLibrary.Jackson, Map.class)
      .transform().groovy("request.body['randNum']")
    ;
    
    from("direct:callRulesSvc")
      .setHeader(Exchange.HTTP_METHOD, constant("POST"))
      .transform().groovy("resource:classpath:/groovy/AccountHolderToDMRequest.groovy")
      .marshal().json(JsonLibrary.Jackson)
      .log(LoggingLevel.DEBUG, log, "${body}")
      .toD("http4://rhdm7-install-kieserver-rhdm7-install-toyota.apps.dallas-309d.openshiftworkshop.com/services/rest/server/containers/instances/DecisionService_1.0.8-SNAPSHOT?authenticationPreemptive=true&authUsername=RAW(dmAdmin)&authPassword=RAW(redhatdm1!)")
      .unmarshal().json(JsonLibrary.Jackson, Map.class)
    ;
    
    from("direct:aggregateToFile")
      .aggregate(header("CorrelationID"))
        .aggregationRepositoryRef("resultsAggregationRepository")
        .aggregationStrategyRef("scoredDataResultsAggregationStrategy")
        .completionSize(header("SplitSize"))
        .log(LoggingLevel.DEBUG, log, "${body}")
        .transform().groovy("resource:classpath:/groovy/AccountHolderToBeanIO.groovy")
        .marshal().beanio("/beanio-mappings.xml", "scoredDataFile")
        .to("file:target/output?fileName=${header.CorrelationID}.txt")
      .end()
    ;
  }
}
