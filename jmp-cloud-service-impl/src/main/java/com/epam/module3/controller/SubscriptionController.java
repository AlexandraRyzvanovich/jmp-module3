package com.epam.module3.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import com.epam.module3.Subscription;
import com.epam.module3.SubscriptionRequestDto;
import com.epam.module3.SubscriptionResponseDto;
import com.epam.module3.SubscriptionService;
import com.epam.module3.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;import org.modelmapper.ModelMapper;
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
import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@Tag(name = "Subscriptions controller", description = "CRUD for subscriptions")
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

  private final ModelMapper modelMapper;
  private final SubscriptionService subscriptionService;

  @PostMapping
  @Operation(
      summary = "Create subscription.",
      description = "Requires user_id. Date of subscription = today")
  public ResponseEntity<SubscriptionResponseDto> createSubscription(
      @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
    Subscription subscription = modelMapper.map(subscriptionRequestDto, Subscription.class);
    Subscription subscriptionRes = subscriptionService.createSubscription(subscription);
    SubscriptionResponseDto subscriptionResponseDto =
        modelMapper.map(subscriptionRes, SubscriptionResponseDto.class);
    subscriptionResponseDto = addHateoas(subscriptionResponseDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionResponseDto);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update subscription.", description = "Requires id of subscription")
  public ResponseEntity<SubscriptionResponseDto> updateSubscription(
      @Parameter(description = "Id of subscription", example = "1") @PathVariable long id,
      @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
    Subscription subscription = modelMapper.map(subscriptionRequestDto, Subscription.class);
    Subscription subscriptionRes = subscriptionService.updateSubscription(id, subscription);
    SubscriptionResponseDto subscriptionResponseDto =
        modelMapper.map(subscriptionRes, SubscriptionResponseDto.class);
    subscriptionResponseDto = addHateoas(subscriptionResponseDto);

    return ResponseEntity.ok().body(subscriptionResponseDto);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete subscription.", description = "Requires id of subscription")
  public ResponseEntity deleteSubscription(
      @Parameter(description = "Id of subscription", example = "1") @PathVariable Long id) {
    subscriptionService.deleteSubscription(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get subscription.", description = "Requires id of subscription")
  public ResponseEntity<SubscriptionResponseDto> getSubscription(
      @Parameter(description = "Id of subscription", example = "1") @PathVariable Long id) {
    Subscription subscription = subscriptionService.getSubscription(id);
    SubscriptionResponseDto subscriptionResponseDto =
        modelMapper.map(subscription, SubscriptionResponseDto.class);
    subscriptionResponseDto = addHateoas(subscriptionResponseDto);

    return ResponseEntity.ok().body(subscriptionResponseDto);
  }

  @GetMapping
  @Operation(
      summary = "Get all subscriptions.",
      description = "Returns all subscriptions with HATEOAS ")
  public List<SubscriptionResponseDto> getAllSubscription() {
    return subscriptionService.getAllSubscriptions().stream()
        .map(subscription -> modelMapper.map(subscription, SubscriptionResponseDto.class))
        .map(this::addHateoas)
        .collect(Collectors.toList());
  }

  private SubscriptionResponseDto addHateoas(SubscriptionResponseDto subscriptionResponseDto) {
    return subscriptionResponseDto
        .add(
            linkTo(
                    methodOn(SubscriptionController.class)
                        .getSubscription(subscriptionResponseDto.getId()))
                .withSelfRel())
        .add(
            linkTo(
                    methodOn(SubscriptionController.class)
                        .createSubscription(new SubscriptionRequestDto()))
                .withRel("create"))
        .add(
            linkTo(
                    methodOn(SubscriptionController.class)
                        .updateSubscription(
                            subscriptionResponseDto.getId(), new SubscriptionRequestDto()))
                .withRel("update"))
        .add(
            linkTo(
                    methodOn(SubscriptionController.class)
                        .deleteSubscription(subscriptionResponseDto.getId()))
                .withRel("delete"))
        .add(linkTo(methodOn(SubscriptionController.class).getAllSubscription()).withRel("all"));
  }
}
