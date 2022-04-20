package com.example.demo.model;

import com.example.demo.entity.Post;
import lombok.Data;
import org.seasar.doma.Entity;

@Data
@Entity
public class PostSearchModel extends Post {
    private String username;
}
