package com.epam.module3;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class SubscriptionRequestDto extends RepresentationModel<SubscriptionRequestDto> {
    private Long id;
    private Long user_id;

}
