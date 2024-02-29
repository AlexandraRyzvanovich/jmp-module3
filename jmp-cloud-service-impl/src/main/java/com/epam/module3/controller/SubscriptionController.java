package com.epam.module3.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.epam.module3.CustomMapping;
import com.epam.module3.Subscription;
import com.epam.module3.SubscriptionConverter;
import com.epam.module3.SubscriptionRequestDto;
import com.epam.module3.SubscriptionResponseDto;
import com.epam.module3.SubscriptionService;
import com.epam.module3.UserService;
import com.epam.module3.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
  private ModelMapper modelMapper;
  private SubscriptionService subscriptionService;

  @Autowired
  public SubscriptionController(
      SubscriptionService subscriptionService, UserService userService, ModelMapper modelMapper) {
    this.subscriptionService = subscriptionService;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  public ResponseEntity<SubscriptionResponseDto> createSubscription(
      @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
    Subscription subscription = modelMapper.map(subscriptionRequestDto, Subscription.class);
    Subscription subscriptionRes = subscriptionService.createSubscription(subscription);
    SubscriptionResponseDto subscriptionResponseDto =
        modelMapper.map(subscriptionRes, SubscriptionResponseDto.class);
    return ResponseEntity.ok().body(subscriptionResponseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SubscriptionResponseDto> updateSubscription(
      @PathVariable long id,  @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
    Subscription subscription = modelMapper.map(subscriptionRequestDto, Subscription.class);
    Subscription subscriptionRes = subscriptionService.updateSubscription(id, subscription);
    SubscriptionResponseDto subscriptionResponseDto =
        modelMapper.map(subscriptionRes, SubscriptionResponseDto.class);
    return ResponseEntity.ok().body(subscriptionResponseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteSubscription(@PathVariable Long id) {
    subscriptionService.deleteSubscription(id);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubscriptionResponseDto> getSubscription(@PathVariable Long id) {
    Subscription subscription = subscriptionService.getSubscription(id);
    SubscriptionResponseDto subscriptionResponseDto =
        modelMapper.map(subscription, SubscriptionResponseDto.class);
    return ResponseEntity.ok().body(subscriptionResponseDto);
  }

  @GetMapping
  public List<SubscriptionResponseDto> getAllSubscription() {
    return subscriptionService.getAllSubscriptions().stream()
        .map(subscription -> modelMapper.map(subscription, SubscriptionResponseDto.class))
        .collect(Collectors.toList());
  }
}
