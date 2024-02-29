package com.epam.module3;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SubscriptionResponseDto {
    private Long id;
    private Long user_id;
    private String startDate;

}
