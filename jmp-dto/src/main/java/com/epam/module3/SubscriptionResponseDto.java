package com.epam.module3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class SubscriptionResponseDto extends RepresentationModel<SubscriptionResponseDto> {
    private Long id;
    private Long user_id;
    private String startDate;

}
