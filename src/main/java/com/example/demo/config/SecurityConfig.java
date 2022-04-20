package com.example.demo.config;

import com.example.demo.security.DemoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Spring-Security用のユーザーアカウント情報を
     * 取得・設定するサービスへのアクセス
     */
    @Autowired
    private DemoUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //初期表示画面を表示する際にログイン画面を表示する
        http.authorizeRequests()
                .mvcMatchers("/", "/signup", "login").permitAll()
                .anyRequest().authenticated() // それ以外は認証が必要
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/signin")// ログインフォームのアクションに指定したURL[action="@{/login}"]を設定
                .usernameParameter("username") // ログインフォームのユーザー欄のname属性を設定
                .passwordParameter("password") // ログインフォームのパスワード欄のname属性を設定
                .successForwardUrl("/index") // ログイン成功時に遷移するURL
                .failureUrl("/login?error") // ログイン失敗時に遷移するURL
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
