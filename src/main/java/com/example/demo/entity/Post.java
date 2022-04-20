package com.example.demo.entity;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

import java.time.LocalDateTime;

@Data
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class Post {

    @Id
    private Long postId;
    private Long accountId;
    private String title;
    private String postContent;
    private LocalDateTime insTs;
}
