package com.virtualmind.test.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TopicCreationDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

}
