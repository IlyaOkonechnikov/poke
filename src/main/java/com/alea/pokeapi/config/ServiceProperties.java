package com.alea.pokeapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("alea.poke-api")
public class ServiceProperties {
  private RequestProperties request;

  public static class RequestProperties {
    private int limit;
  }
}
