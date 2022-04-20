package com.example.demo.repository;

import com.example.demo.criteria.PostIndexCriteria;
import com.example.demo.dao.PostDao;
import com.example.demo.entity.Post;
import com.example.demo.model.PostSearchModel;
import lombok.RequiredArgsConstructor;
import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final PostDao postDao;

    public List<PostSearchModel> selectByCriteria(PostIndexCriteria criteria, SelectOptions options) {
        return postDao.selectByCriteria(criteria, options, toList());
    }

    public void insert(Post entity) {
        postDao.insert(entity);
    }
}
