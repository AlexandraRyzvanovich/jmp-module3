package com.epam.module3.config;

import com.epam.module3.utils.SubscriptionConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public ModelMapper modelMapper(SubscriptionConverter subscriptionConverter) {
    ModelMapper mapper = new ModelMapper();

    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    mapper.addConverter(subscriptionConverter.entityToResponseDto);
    mapper.addConverter(subscriptionConverter.requestDtoToEntity);

    return mapper;
  }
}
