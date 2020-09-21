package com.dog.admin.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * 基于数据库rbac模型的url权限校验
 * @author yangxi
 */
@Component
public class DbAuthorityServiceImpl {

	/**
	 * uri匹配工具
	 */
	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	public boolean check(Authentication authentication, HttpServletRequest request) {

		// 没有经过认证的用户一律不放行
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return false;
		}

		// 请求方式
		String requestMethod = request.getMethod();
		// 请求uri /login
		String requestUri = request.getRequestURI();

//		// 请求url http://localhost:8080/login
//		String requestUrl = request.getRequestURL().toString();


		Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
		if(grantedAuthorities != null && !grantedAuthorities.isEmpty()) {
			for(GrantedAuthority grantedAuthority : grantedAuthorities) {
				if(grantedAuthority instanceof UrlGrantedAuthority) {
					UrlGrantedAuthority urlGrantedAuthority = (UrlGrantedAuthority)grantedAuthority;
					List<UrlAuthorityDetails> urlAuthorityDetailsList = urlGrantedAuthority.getUrlAuthorityDetailsList();
					if(urlAuthorityDetailsList != null && !urlAuthorityDetailsList.isEmpty()) {
						for(UrlAuthorityDetails urlAuthorityDetails : urlAuthorityDetailsList) {
							String method = urlAuthorityDetails.getMethod();
							String url = urlAuthorityDetails.getUrl();

							// 请求method与uri都有权限服务中配置的数据匹配，那么就认为用户有权限访问这个url
							if (requestMethod.equalsIgnoreCase(method) && antPathMatcher.match(url, requestUri)) {
								return true;
							}


						}
					}
				}

			}
		}

		return true;
	}
}