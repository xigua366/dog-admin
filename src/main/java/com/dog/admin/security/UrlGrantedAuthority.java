package com.dog.admin.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 * @date 2020-09-20 21:58
 */
public class UrlGrantedAuthority implements GrantedAuthority {

    /**
     * 用户角色
     */
    private String role;

    /**
     * 当前角色下拥有的api权限
     */
    private List<UrlAuthorityDetails> urlAuthorityDetailsList;

    public UrlGrantedAuthority(String role, List<UrlAuthorityDetails> urlAuthorityDetailsList) {
        this.role = role;
        this.urlAuthorityDetailsList = urlAuthorityDetailsList;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public List<UrlAuthorityDetails> getUrlAuthorityDetailsList() {
        return this.urlAuthorityDetailsList;
    }
}
