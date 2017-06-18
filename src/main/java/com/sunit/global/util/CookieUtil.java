package com.sunit.global.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	private HttpServletRequest request;

	public CookieUtil(HttpServletRequest request) {
		this.setRequest(request);
	}

	private void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Cookie getCookie(String cookieName) {
		Cookie[] cs = request.getCookies();
		Cookie cookie = null;
		if (cs != null)
			for (int i = 0; i < cs.length; i++) {
				if (cs[i].getName().equals("carts")) {
					cookie = cs[i];
					break;
				}
			}
		return cookie;
	} 
	 
	
	public Cookie setCookieValue(Cookie cookie,String value){
		cookie.setMaxAge(3600*24*365); 
		cookie.setPath("/"); 
		cookie.setValue(value);
		return cookie;
		
	}
}
