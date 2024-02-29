package com.epam.module3;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class UserRequestDto {
    private Long id;

    private String first_name;

    private String last_name;

    private LocalDate birthday;

}
