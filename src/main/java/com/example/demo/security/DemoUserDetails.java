package com.example.demo.security;

import com.example.demo.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class DemoUserDetails implements UserDetails {

    private Account account;

    private Collection<GrantedAuthority> authorities;

    /**
     * Spring-Security用のユーザーアカウント情報(UserDetails)を作成する
     *
     * @param account     UserPassオブジェクト
     * @param authorities ユーザー権限情報
     */
    public DemoUserDetails(Account account, Collection<GrantedAuthority> authorities) {
        this.account = account;
        this.authorities = authorities;
    }

    /**
     * ユーザー権限情報を取得する
     *
     * @return ユーザー権限情報
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * パスワードを取得する
     *
     * @return パスワード
     */
    @Override
    public String getPassword() {
        return account.getPassword();
    }

    /**
     * ユーザー名を取得する
     *
     * @return ユーザー名
     */
    @Override
    public String getUsername() {
        return account.getUsername();
    }

    /**
     * アカウントが期限切れでないかを取得する
     *
     * @return アカウントが期限切れでないか
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * アカウントがロックされていないかを取得する
     *
     * @return アカウントがロックされていないか
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * アカウントが認証期限切れでないかを取得する
     *
     * @return アカウントが認証期限切れでないか
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * アカウントが利用可能かを取得する
     *
     * @return アカウントが利用可能か
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
