package com.cos.blog.config.ouath.provider;

import java.util.Map;

public class NaverUserInfo2 implements OAuth2UserInfo{

	private Map<String, Object> attributes;//=oauth2User.getAttributes()
	
	public NaverUserInfo2(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	/*
	 * responses==
	 * {
	 * id=cSG9-d4fpi42Vn9FVhrB0n3q5h_PLk5tJ9OGg8dXyEg,
	 * email=jun991223@naver.com, 
	 * name=권영준
	 * }
	 */
	@Override
	public String getuserid() {
		return (String) attributes.get("id");
	}

	@Override
	public String getouath() {
		return "naver";
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}
    

}
