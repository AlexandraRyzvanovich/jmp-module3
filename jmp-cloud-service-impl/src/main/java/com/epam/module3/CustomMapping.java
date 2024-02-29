package com.epam.module3;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomMapping implements Converter<SubscriptionRequestDto, Subscription> {

  private UserService userService;

  public CustomMapping(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Subscription convert(MappingContext<SubscriptionRequestDto, Subscription> context) {
    SubscriptionRequestDto source = context.getSource();
    Subscription destination = new Subscription();
    // destination.setId(source.getId());
    destination.setUser(userService.getUser(source.getUser_id()));
    return destination;
  }
}
