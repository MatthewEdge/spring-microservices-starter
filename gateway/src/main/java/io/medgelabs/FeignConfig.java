package io.medgelabs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;
import feign.Retryer;

@Configuration
public class FeignConfig {

  @Bean
  public Retryer retryer() {
    return new Retryer.Default();
  }

  @Bean
  public Request.Options options() {
    // 3 second timeout for connect and read
    return new Request.Options(3000, 3000, false);
  }

  @Bean
  public Logger.Level logLevel() {
    return Logger.Level.NONE;
  }
}
