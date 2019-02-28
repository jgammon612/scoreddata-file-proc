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

import java.net.SocketException;
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
import org.apache.camel.component.infinispan.InfinispanComponent;
import org.apache.camel.component.infinispan.InfinispanConstants;
import org.apache.camel.component.infinispan.InfinispanOperation;
import org.apache.camel.component.sql.SqlComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.processor.aggregate.jdbc.JdbcAggregationRepository;
import org.apache.camel.spi.ComponentCustomizer;
import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.apache.camel.util.toolbox.AggregationStrategies;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class CamelConfiguration extends RouteBuilder {

  private static final Logger log = LoggerFactory.getLogger(CamelConfiguration.class);

  @Autowired
  ApplicationProperties app;
  
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
  GlobalConfiguration globalCacheConfiguration() {
    return GlobalConfigurationBuilder
            .defaultClusteredBuilder()
            .nonClusteredDefault()
            .globalJmxStatistics()
            .disable()
            .build();
  }
  
  @Bean
  CacheContainer cacheContainer(GlobalConfiguration globalCacheConfiguration) {
    return new DefaultCacheManager(globalCacheConfiguration);
  }
  
  @Bean
  ComponentCustomizer<InfinispanComponent> infinispanComponentCustomizer(CacheContainer cacheContainer) {
    return (component) -> {
      component.setCacheContainer(cacheContainer);
    };
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
      /*
      List<Integer> list;
      if (oldExchange == null) {
        oldExchange = newExchange;
        list = new ArrayList<>();
      } else {
        list = oldExchange.getIn().getBody(List.class);
      }
      list.add(newExchange.getIn().getHeader("SplitIndex", Integer.class));
      oldExchange.getIn().setBody(list);
      */
      if (oldExchange == null) {
        oldExchange = newExchange;
      }
      oldExchange.getIn().setBody(null);
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
      if (newExchange.isFailed()) {
        Exception exception = newExchange.getException(Exception.class);
        if (exception instanceof SocketException) {
          throw new RuntimeException(exception);
        }
        accountHolder.setErrorMessage(exception.getMessage());
        oldExchange.getIn().setHeader("Failed", true);
      } else {
        List<String> codes = newExchange.getIn().getBody(List.class);
        boolean mismatch = true;
        if (codes.contains(accountHolder.getEphone()) ||
            codes.contains(accountHolder.getHphone())) {
          mismatch = false;
        }
        accountHolder.setMismatch((mismatch)?"Y":"N");
      }
      return oldExchange;
    };
  }
  
  @Bean
  AggregationStrategy skipIdEnrichmentStrategy() {
    return (Exchange oldExchange, Exchange newExchange) -> {
      AccountHolder accountHolder = oldExchange.getIn().getBody(AccountHolder.class);
      if (newExchange.isFailed()) {
        Exception exception = newExchange.getException(Exception.class);
        if (exception instanceof SocketException) {
          throw new RuntimeException(exception);
        }
        accountHolder.setErrorMessage(exception.getMessage());
        oldExchange.getIn().setHeader("Failed", true);
      } else {
        Double randNum = newExchange.getIn().getBody(Double.class);
        accountHolder.setSkip_ID(randNum);
      }
      return oldExchange;
    };
  }
  
  @Bean
  AggregationStrategy rulesEnrichmentStrategy() {
    return (Exchange oldExchange, Exchange newExchange) -> {
      AccountHolder accountHolder = oldExchange.getIn().getBody(AccountHolder.class);
      if (newExchange.isFailed()) {
        Exception exception = newExchange.getException(Exception.class);
        if (exception instanceof SocketException) {
          throw new RuntimeException(exception);
        }
        accountHolder.setErrorMessage(exception.getMessage());
        oldExchange.getIn().setHeader("Failed", true);
      } else {
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
      }
      return oldExchange;
    };
  }
  
  @Override
  public void configure() throws Exception {
    
    from("file:{{app.file-reader.path}}?delete=true&moveFailed=.failed")
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
        .constant("direct:callNpaSvcCacheOrBackend")
        .aggregationStrategyRef("mismatchEnrichmentStrategy")
        .aggregateOnException(true)
      .end()
      .filter(header("Failed"))
        .to("direct:aggregateToFile")
        .stop()
      .end()
      .enrich()
        .constant("direct:callRandNumSvcCacheOrBackend")
        .aggregationStrategyRef("skipIdEnrichmentStrategy")
        .aggregateOnException(true)
      .end()
      .filter(header("Failed"))
        .to("direct:aggregateToFile")
        .stop()
      .end()
      .enrich()
        .constant("direct:callRulesSvc")
        .aggregationStrategyRef("rulesEnrichmentStrategy")
        .aggregateOnException(true)
      .end()
      .to("direct:aggregateToFile")
    ;
    
    from("direct:callNpaSvcCacheOrBackend")
      .log(LoggingLevel.DEBUG, log, "Trying npa service cache for: ${body.getID()}")
      .setHeader(InfinispanConstants.KEY, simple("${body.getSTATE()}"))
      .setHeader(InfinispanConstants.RESULT_HEADER, constant(InfinispanConstants.RESULT))
      .to("infinispan:npaByStateCache?operation=GET")
      .filter(header(InfinispanConstants.RESULT).isNotNull())
        .setBody(header(InfinispanConstants.RESULT))
        .stop()
      .end()
      .to("direct:callNpaSvc")
      .setHeader(InfinispanConstants.VALUE, simple("${body}"))
      .setHeader(InfinispanConstants.IGNORE_RETURN_VALUES, constant(true))
      .setHeader(InfinispanConstants.LIFESPAN_TIME, constant(app.getNpaClient().getCacheTimeout()))
      .setHeader(InfinispanConstants.LIFESPAN_TIME_UNIT, constant("MILLISECONDS"))
      .to("infinispan:npaByStateCache?operation=PUT")
    ;
    
    from("direct:callNpaSvc")
      .log(LoggingLevel.DEBUG, log, "Invoking npa service for: ${body.getID()}")
      .setHeader(Exchange.HTTP_METHOD, constant("GET"))
      .setHeader("state", simple("${body.getSTATE()}"))
      .setBody(constant(null))
      .toD("http4://{{app.npa-client.host}}:{{app.npa-client.port}}/camel/npa/by-state/${header.state}")
      .unmarshal().json(JsonLibrary.Jackson, Map.class)
      .transform().groovy("request.body['codes']")
    ;
    
    from("direct:callRandNumSvcCacheOrBackend")
      .log(LoggingLevel.DEBUG, log, "Trying randnum service cache for: ${body.getID()}")
      .setHeader(InfinispanConstants.KEY, simple("${body.getID()}"))
      .setHeader(InfinispanConstants.RESULT_HEADER, constant(InfinispanConstants.RESULT))
      .to("infinispan:randnumByIdCache?operation=GET")
      .filter(header(InfinispanConstants.RESULT).isNotNull())
        .setBody(header(InfinispanConstants.RESULT))
        .stop()
      .end()
      .to("direct:callRandNumSvc")
      .setHeader(InfinispanConstants.VALUE, simple("${body}"))
      .setHeader(InfinispanConstants.IGNORE_RETURN_VALUES, constant(true))
      .setHeader(InfinispanConstants.LIFESPAN_TIME, constant(app.getRandNumClient().getCacheTimeout()))
      .setHeader(InfinispanConstants.LIFESPAN_TIME_UNIT, constant("MILLISECONDS"))
      .to("infinispan:randnumByIdCache?operation=PUT")
    ;
    
    from("direct:callRandNumSvc")
      .log(LoggingLevel.DEBUG, log, "Invoking randnum service for: ${body.getID()}")
      .setHeader(Exchange.HTTP_METHOD, constant("GET"))
      .setHeader("accountNum", simple("${body.getID()}"))
      .setBody(constant(null))
      .toD("http4://{{app.rand-num-client.host}}:{{app.rand-num-client.port}}/camel/randnum/${header.accountNum}")
      .unmarshal().json(JsonLibrary.Jackson, Map.class)
      .transform().groovy("request.body['randNum']")
    ;
    
    from("direct:callRulesSvc")
      .log(LoggingLevel.DEBUG, log, "Invoking rules service for: ${body.getID()}")
      .setHeader(Exchange.HTTP_METHOD, constant("POST"))
      .transform().groovy("resource:classpath:/groovy/AccountHolderToDMRequest.groovy")
      .marshal().json(JsonLibrary.Jackson)
      .toD("http4://{{app.rules-client.host}}:{{app.rules-client.port}}/services/rest/server/containers/instances/{{app.rules-client.instance}}?authenticationPreemptive=true&authUsername=RAW({{app.rules-client.username}})&authPassword=RAW({{app.rules-client.password}})")
      .unmarshal().json(JsonLibrary.Jackson, Map.class)
    ;
    
    from("direct:aggregateToFile")
      .log(LoggingLevel.DEBUG, log, "Inserting claimcheck records for: correlationId=[${header.CorrelationID}], splitIndex=[${header.SplitIndex}]")
      .marshal().json(JsonLibrary.Jackson)
      .to("sql:INSERT INTO SCORED_DATA_RESULTS_AGGREGATION_claimcheck VALUES (:#${header.CorrelationID}, :#${header.SplitIndex}, :#${body}) ON DUPLICATE KEY UPDATE body=:#${body}")
      .setBody(constant(null))
      .aggregate(header("CorrelationID"))
        .aggregationRepositoryRef("resultsAggregationRepository")
        .aggregationStrategyRef("scoredDataResultsAggregationStrategy")
        .completionSize(header("SplitSize"))
        .log(LoggingLevel.DEBUG, log, "Querying claimchecked records for: [${header.CorrelationID}]")
        .to("sql:SELECT body FROM SCORED_DATA_RESULTS_AGGREGATION_claimcheck WHERE id=:#${header.CorrelationID}")
        .split(body(), AggregationStrategies.groupedBody())
          .parallelProcessing()
          .transform().groovy("request.body['body']")
          .unmarshal().json(JsonLibrary.Jackson, AccountHolder.class)
        .end()
        .log(LoggingLevel.DEBUG, log, "Writing file: [${header.CorrelationID}.txt]")
        .transform().groovy("resource:classpath:/groovy/AccountHolderToBeanIO.groovy")
        .marshal().beanio("/beanio-mappings.xml", "scoredDataFile")
        .to("file:{{app.file-writer.path}}?fileName=${header.CorrelationID}.txt")
        .log(LoggingLevel.DEBUG, log, "Deleting claimchecked records for: [${header.CorrelationID}]")
        .to("sql:DELETE FROM SCORED_DATA_RESULTS_AGGREGATION_claimcheck WHERE id=:#${header.CorrelationID}")
      .end()
    ;
  }
}
