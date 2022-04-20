package com.example.demo.controller.input;

import com.example.demo.criteria.PostIndexCriteria;
import com.example.demo.taglib.params.QueryParamInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.LinkedHashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostIndexParameter implements QueryParamInterface {

    @Nullable
    private String searchUsername;

    @Nullable
    private String searchTitle;

    @Nullable
    private String searchKeyword;

    public PostIndexCriteria toCriteria() {
        return PostIndexCriteria.builder()
                .searchUsername(searchUsername)
                .searchTitle(searchTitle)
                .searchKeyword(searchKeyword)
                .build();
    }

    @Override
    public LinkedHashMap<String, Object> getParamsMap() {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("searchUsername", searchUsername);
        params.put("searchTitle", searchTitle);
        params.put("searchKeyword", searchKeyword);
        return params;
    }
}
