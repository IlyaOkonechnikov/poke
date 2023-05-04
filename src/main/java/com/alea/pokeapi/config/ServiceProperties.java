package com.alea.pokeapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("alea.poke-api")
public class ServiceProperties {
  private int topLimit;
}
