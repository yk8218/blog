package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.blog.model.User;

import lombok.Data;
import lombok.Getter;
//52강 9:00
//principal (접근 주체) = 세션처럼 사용 = Spring Security Context 에 보관됨
@Data
public class PrincipalDetail implements UserDetails, OAuth2User{

	private static final long serialVersionUID = 7645618956884452156L;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	
	private User user;
	private Map<String, Object> attributes;
	
	
	
	
	public PrincipalDetail(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	public User getUser() {
		return user;
		
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	// 계정의 비밀번호를 리턴한다.
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// 계정의 이름을 리턴한다.
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 우리는 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<SimpleGrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
				
		return collectors;
	}
	
	
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
	public String getName() {
		return null;
	}
	
}