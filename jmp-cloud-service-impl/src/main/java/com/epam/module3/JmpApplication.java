package com.epam.module3;

import com.epam.module3.utils.SubscriptionConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JmpApplication {

  public static void main(String[] args) {
    SpringApplication.run(JmpApplication.class, args);
  }
}
