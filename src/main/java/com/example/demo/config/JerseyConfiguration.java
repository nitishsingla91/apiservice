package com.example.demo.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.example.demo.controller.OBController;


@Configuration
@ApplicationPath("/openbanktest")
public class JerseyConfiguration extends ResourceConfig {
  public JerseyConfiguration() {
  
  }
 
  @PostConstruct
  public void setUp() {
	  register(OBController.class);
  }
  
  
}
