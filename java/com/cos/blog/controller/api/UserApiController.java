package com.cos.blog.controller.api;

import java.beans.Encoder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	/*
	 * @Autowired private HttpSession session;
	 */

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username,password,email
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 됨
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user, @AuthenticationPrincipal PrincipalDetail principal,
			HttpSession session) { /* @RequestBody json 받고싶으면 이거적으면됨 */
		userService.회원수정(user);

		// 세션 등록
		String encPassword = encoder.encode(user.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}

/*
 * Authentication authentication = new
 * UsernamePasswordAuthenticationToken(principal, null,
 * principal.getAuthorities()); SecurityContext securityContext =
 * SecurityContextHolder.getContext();
 * securityContext.setAuthentication(authentication);
 * session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
 */