package com.alea.pokeapi.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.alea.pokeapi.service")
@EnableConfigurationProperties(ServiceProperties.class)
public class ServiceConfig {
}
