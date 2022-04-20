package com.example.demo.dao;

import com.example.demo.criteria.PostIndexCriteria;
import com.example.demo.entity.Post;
import com.example.demo.model.PostSearchModel;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.SelectType;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.stream.Collector;

@ConfigAutowireable
@Dao
public interface PostDao {

    @Select(strategy = SelectType.COLLECT)
    <R> R selectByCriteria(PostIndexCriteria criteria, SelectOptions options, Collector<PostSearchModel, ?, R> collector);

    @Insert(sqlFile = true)
    int insert(Post entity);
}
