package com.commute.direction.cfg;

import com.google.maps.GeoApiContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

@Slf4j
@Configuration
public class GeoApiContextCfg {

  @Bean
  public GeoApiContext createGeoApiContext(SystemProperties systemProperties){
    Assert.notNull(systemProperties.getSecurity().getApiKeyBased().getGoogleMaps().getApiKey(), "Api key must not be null");

    GeoApiContext apiContext = new GeoApiContext.Builder()
      .apiKey(systemProperties.getSecurity().getApiKeyBased().getGoogleMaps().getApiKey())
      .build();
    return apiContext;
  }
}
