package com.virtualmind.test.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TopicUpdateDTO {

    @NotBlank(message = "Topic name cannot be blank")
    private String name;

}
