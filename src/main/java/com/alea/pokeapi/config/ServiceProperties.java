package com.alea.pokeapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("alea.poke-api")
public class ServiceProperties {
  private TopProperties top;

  public static class TopProperties {
    private int limit;
  }
}
