package com.dog.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yangxi
 * @version 1.0
 * @date 2020-09-20 20:31
 */
@Configuration
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.formLogin();

        http.authorizeRequests().antMatchers( "/login").permitAll();

        http.authorizeRequests().anyRequest().access("@dbAuthorityServiceImpl.check(authentication,request)");
    }


}
