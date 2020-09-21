package com.dog.admin.security;

import com.dog.admin.domain.AccountDTO;
import com.dog.admin.service.AccountService;
import com.dog.admin.service.PriorityService;
import com.dog.admin.service.impl.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 * @date 2020-09-20 20:36
 */
@Component
public class DefaultUserDetailsServiceImpl implements UserDetailsService {

    private static Logger log = LoggerFactory.getLogger(DefaultUserDetailsServiceImpl.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private PriorityService priorityService;

    public DefaultUserDetailsServiceImpl(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountDTO accountDTO;
        List<Priority> priorityList = null;
        try {
            accountDTO = accountService.getByUsername(username);
            if(accountDTO == null) {
                throw new UsernameNotFoundException("用户名【" + username + "】不存在");
            }
            priorityList = priorityService.listAuthorizedByAccountId(accountDTO.getId());
        } catch(Exception e) {
            log.error("error", e);
            throw new UsernameNotFoundException("查询用户【" + username + "】失败");
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

        if(priorityList != null && !priorityList.isEmpty()) {

            // 查询出用户拥有的角色与权限
            List<UrlAuthorityDetails> urlAuthorityDetailsList = new ArrayList<>();

            for(Priority priority : priorityList) {
                UrlAuthorityDetails urlAuthorityDetails = new UrlAuthorityDetails("GET", priority.getUrl());
                urlAuthorityDetailsList.add(urlAuthorityDetails);
            }

            UrlGrantedAuthority urlGrantedAuthority = new UrlGrantedAuthority("ROLE_OTHER", urlAuthorityDetailsList);
            grantedAuthorityList.add(urlGrantedAuthority);
        }

        String password = accountDTO.getPassword();
        return new User(username, password, grantedAuthorityList);
    }
}
