package com.dog.admin.security;

/**
 * @author yangxi
 * @version 1.0
 * @date 2020-09-20 22:01
 */
public class UrlAuthorityDetails {

    private String method;

    private String url;

    public UrlAuthorityDetails(String method, String url) {
        this.method = method;
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
