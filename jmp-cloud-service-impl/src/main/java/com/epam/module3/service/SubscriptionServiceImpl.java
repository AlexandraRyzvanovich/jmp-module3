package com.epam.module3.service;

import com.epam.module3.Subscription;
import com.epam.module3.SubscriptionRepository;
import com.epam.module3.SubscriptionService;
import com.epam.module3.User;
import com.epam.module3.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;
  private final UserRepository userRepository;

  @Override
  public List<Subscription> getAllSubscriptions() {
    return subscriptionRepository.findAll();
  }

  @Override
  public Subscription createSubscription(Subscription subscription) {
    subscription.setStartDate(LocalDate.now());
    return subscriptionRepository.save(subscription);
  }

  @Override
  public Subscription updateSubscription(Long id, Subscription subscription) {
    Subscription subscriptionRes =
        subscriptionRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    User user =
        userRepository
            .findById(subscription.getUser().getId())
            .orElseThrow(ResourceNotFoundException::new);
    subscription.setUser(user);
    subscription.setStartDate(LocalDate.now());
    return subscriptionRepository.save(subscriptionRes);
  }

  @Override
  public void deleteSubscription(Long id) {
    Subscription subscription =
        subscriptionRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    subscriptionRepository.delete(subscription);
  }

  @Override
  public Subscription getSubscription(Long id) {
    return subscriptionRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }
}
