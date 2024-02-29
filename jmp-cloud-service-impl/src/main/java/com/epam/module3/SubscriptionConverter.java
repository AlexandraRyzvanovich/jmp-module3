package com.epam.module3;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;

public class SubscriptionConverter {
  @Autowired private UserService userService;
  //
  //  public SubscriptionConverter(UserService userService) {
  // this.userService=userService;
  // }

  public Converter<SubscriptionRequestDto, Subscription> requestDtoToEntity =
      new Converter<SubscriptionRequestDto, Subscription>() {
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
      new Converter<Subscription, SubscriptionResponseDto>() {
        @Override
        public SubscriptionResponseDto convert(
            MappingContext<Subscription, SubscriptionResponseDto> mappingContext) {
          SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
          subscriptionResponseDto.setId(mappingContext.getSource().getId());
          subscriptionResponseDto.setUser_id(mappingContext.getSource().getUser().getId());
          subscriptionResponseDto.setStartDate(
              mappingContext.getSource().getStartDate().toString());
          return mappingContext
              .getMappingEngine()
              .map(
                  mappingContext.create(
                      subscriptionResponseDto, mappingContext.getDestinationType()));
        }
      };
}
