package com.epam.module3.utils;

import com.epam.module3.Subscription;
import com.epam.module3.SubscriptionRequestDto;
import com.epam.module3.SubscriptionResponseDto;
import com.epam.module3.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionConverter {
  private final UserService userService;

  public Converter<SubscriptionRequestDto, Subscription> requestDtoToEntity =
      new Converter<>() {
        @Override
        public Subscription convert(
            MappingContext<SubscriptionRequestDto, Subscription> mappingContext) {
          Subscription subscription = new Subscription();
          subscription.setUser(userService.getUser(mappingContext.getSource().getUser_id()));
          return mappingContext
              .getMappingEngine()
              .map(mappingContext.create(subscription, mappingContext.getDestinationType()));
        }
      };

  public Converter<Subscription, SubscriptionResponseDto> entityToResponseDto =
      mappingContext -> {
        SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
        subscriptionResponseDto.setId(mappingContext.getSource().getId());
        subscriptionResponseDto.setUser_id(mappingContext.getSource().getUser().getId());
        subscriptionResponseDto.setStartDate(mappingContext.getSource().getStartDate().toString());
        return mappingContext
            .getMappingEngine()
            .map(
                mappingContext.create(
                    subscriptionResponseDto, mappingContext.getDestinationType()));
      };
}
