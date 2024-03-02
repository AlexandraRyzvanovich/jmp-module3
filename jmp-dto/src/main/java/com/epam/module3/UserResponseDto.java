package com.epam.module3;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class UserResponseDto extends RepresentationModel<UserResponseDto> {
    private Long id;
    private String first_name;
    private String last_name;
    private String birthday;


}
