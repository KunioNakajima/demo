package com.example.demo.security;

import com.example.demo.entity.Account;
import com.example.demo.exception.DuplicateException;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DemoUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 指定したユーザー名をもつSpring-Security用のユーザーアカウント情報を取得する
     *
     * @param username ユーザー名
     * @return 指定したユーザー名をもつSpring-Security用のユーザーアカウント情報
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("ユーザー名を入力してください");
        }
        //指定したユーザー名をもつAccountオブジェクトを取得する
        List<Account> account = accountRepository.selectByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません");
        }
        //指定したユーザー名をもつSpring-Security用のユーザーアカウント情報を取得する
        return new DemoUserDetails(account.get(0), AuthorityUtils.createAuthorityList("USER"));
    }

    @Transactional
    public Long register(String username, String password) {
        //指定したユーザー名をもつUserPassオブジェクトを取得する
        List<Account> accounts = accountRepository.selectByUsername(username);
        //UserPassオブジェクトが無ければ追加・あれば更新する
        if (accounts.size() == 0) {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(password);
            accountRepository.insert(account);
            List<Account> createAccount = accountRepository.selectByUsername(username);
            return createAccount.get(0).getAccountId();
        } else {
            throw new DuplicateException(username, "/signup");
        }
    }
}