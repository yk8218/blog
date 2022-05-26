package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.config.ouath.PrincipalOauth2UserService;


@Configuration // 빈 등록 (객체 생성)
@EnableWebSecurity // 필터 체인에 등록 (스프링 시큐리티 활성화)
@EnableGlobalMethodSecurity(prePostEnabled=true) // 특정 주소 접근시 권한 및 인증을 pre(미리) 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// 1. Bean 어노테이션은 메서드에 붙여서 객체 생성시 사용
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 2. 시큐리티가 로그인할 때 어떤 암호화로 인코딩해서 비번을 비교할지 알려줘야 함.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	// 3. 필터링
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
	    .authorizeRequests()
	    .antMatchers("/", "/css/**", "/image/**", "/js/**", "/auth/**", "/dummy/**").permitAll()
	    .antMatchers("/api/v1/**")
        .permitAll()
	    .anyRequest()
	    .authenticated()
	    .and()
	    .formLogin()
	    .loginPage("/auth/loginForm")
	    .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청하는 로그인을 가로채서 대신 로그인
	    .defaultSuccessUrl("/")
		.and()
		.oauth2Login()
		.userInfoEndpoint()
		.userService(principalOauth2UserService);
	}
	
	// 참고 : .headers().frameOptions().disable() // 아이프레임 접근 막기
	// 참고 : .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어주는 것이 좋음)
}