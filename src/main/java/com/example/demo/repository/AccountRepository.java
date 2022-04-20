package com.example.demo.repository;

import com.example.demo.dao.AccountDao;
import com.example.demo.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final AccountDao accountDao;

    public List<Account> selectByUsername(String username) {
        return accountDao.selectByUsername(username);
    }

    public void insert(Account entity) {
        accountDao.insert(entity);
    }
}
