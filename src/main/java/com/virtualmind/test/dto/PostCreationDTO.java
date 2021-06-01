package com.virtualmind.test.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostCreationDTO {

    @NotNull(message = "Invalid topic for post creation")
    private Integer topicId;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String text;
}
