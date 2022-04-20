package com.example.demo.controller.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@ToString
public class PostForm {
    public static final String ATTRIBUTE_NAME = "postForm";

    @Size(max = 20, message = "{default.invalid.max.size.message}")
    private String title;

    @NotNull(message = "{default.blank.message}")
    @Size(max = 100, message = "{default.invalid.max.size.message}")
    private String postContent;
}
