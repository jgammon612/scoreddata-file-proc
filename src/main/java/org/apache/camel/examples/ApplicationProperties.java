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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

  private FileReaderProperties fileReader;
  private FileWriterProperties fileWriter;
  private NpaClientProperties npaClient;
  private RandNumClientProperties randNumClient;
  private RulesClientProperties rulesClient;

  public FileReaderProperties getFileReader() {
    return fileReader;
  }

  public void setFileReader(FileReaderProperties fileReader) {
    this.fileReader = fileReader;
  }

  public FileWriterProperties getFileWriter() {
    return fileWriter;
  }

  public void setFileWriter(FileWriterProperties fileWriter) {
    this.fileWriter = fileWriter;
  }

  public NpaClientProperties getNpaClient() {
    return npaClient;
  }

  public void setNpaClient(NpaClientProperties npaClient) {
    this.npaClient = npaClient;
  }

  public RandNumClientProperties getRandNumClient() {
    return randNumClient;
  }

  public void setRandNumClient(RandNumClientProperties randNumClient) {
    this.randNumClient = randNumClient;
  }

  public RulesClientProperties getRulesClient() {
    return rulesClient;
  }

  public void setRulesClient(RulesClientProperties rulesClient) {
    this.rulesClient = rulesClient;
  }
  
  public static class FileReaderProperties {
    
    private String path;

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }
  }
  
  public static class FileWriterProperties {
    
    private String path;

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }
  }
  
  public static class NpaClientProperties {
    
    private String host = "localhost";
    private int port = 80;
    private long cacheTimeout = 60000L;

    public String getHost() {
      return host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public int getPort() {
      return port;
    }

    public void setPort(int port) {
      this.port = port;
    }

    public long getCacheTimeout() {
      return cacheTimeout;
    }

    public void setCacheTimeout(long cacheTimeout) {
      this.cacheTimeout = cacheTimeout;
    }
  }
  
  public static class RandNumClientProperties {
    
    private String host = "localhost";
    private int port = 80;
    private long cacheTimeout = 60000L;

    public String getHost() {
      return host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public int getPort() {
      return port;
    }

    public void setPort(int port) {
      this.port = port;
    }

    public long getCacheTimeout() {
      return cacheTimeout;
    }

    public void setCacheTimeout(long cacheTimeout) {
      this.cacheTimeout = cacheTimeout;
    }
  }
  
  public static class RulesClientProperties {
    
    private String host = "localhost";
    private int port = 80;
    private String username;
    private String password;

    public String getHost() {
      return host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public int getPort() {
      return port;
    }

    public void setPort(int port) {
      this.port = port;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}
