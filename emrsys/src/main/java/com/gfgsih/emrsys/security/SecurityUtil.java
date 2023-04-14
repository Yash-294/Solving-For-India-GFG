package com.gfgsih.emrsys.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
	public static String getSessionUser() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			String currDoc=auth.getName();
			return currDoc;
		}
		return null;
	}

}
