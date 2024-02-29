package com.epam.module3;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SubscriptionRequestDto {
    private Long id;
    private Long user_id;

}
