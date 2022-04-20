package com.example.demo.dao;

import com.example.demo.entity.Account;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface AccountDao {

    @Select
    List<Account> selectByUsername(String username);

    @Insert(sqlFile = true)
    int insert(Account entity);
}
