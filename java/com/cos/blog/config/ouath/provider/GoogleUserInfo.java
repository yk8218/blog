package com.cos.blog.config.ouath.provider;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{

	private Map<String, Object> attributes;//=oauth2User.getAttributes()
	
	public GoogleUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public String getuserid() {
		return (String) attributes.get("sub");
	}

	@Override
	public String getouath() {
		return "google";
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
