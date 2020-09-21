package com.dog.admin.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxi
 * @version 1.0
 * @date 2020-09-20 20:53
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 从上下文中获取用户认证信息
     * @return
     */
    @GetMapping("/me")
    public Object getUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
