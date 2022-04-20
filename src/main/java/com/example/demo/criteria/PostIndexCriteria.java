package com.example.demo.criteria;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostIndexCriteria {
    private String searchUsername;
    private String searchTitle;
    private String searchKeyword;
}
