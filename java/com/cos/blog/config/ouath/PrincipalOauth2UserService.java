package com.cos.blog.config.ouath;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.ouath.provider.GoogleUserInfo;
import com.cos.blog.config.ouath.provider.NaverUserInfo2;
import com.cos.blog.config.ouath.provider.OAuth2UserInfo;
import com.cos.blog.controller.UserController;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private BoardService boardService;

	@Autowired
	private UserController userController;

	@Autowired
	private UserRepository userRepository;

	@Value("${cos.key}")
	private String cosKey;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration==" + userRequest.getClientRegistration());
		System.out.println("getAccessToken==" + userRequest.getAccessToken().getTokenValue());

		OAuth2User oauth2User = super.loadUser(userRequest);
		

		System.out.println("getAttributes==" + oauth2User.getAttributes());
		System.out.println("responsesid==" + oauth2User.getAttributes().get("id"));
		System.out.println("responses==" + oauth2User.getAttributes().get("response"));

		
		 OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청입니다");
			oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인 요청입니다");
			oAuth2UserInfo = new NaverUserInfo2((Map)oauth2User.getAttributes().get("response"));
		} else {
			System.out.println("구글,카카오톡,네이버만 지원합니다.");
		}
		
		
        String username1 = oAuth2UserInfo.getName();
        String email1 = oAuth2UserInfo.getEmail();
        String oauthType = oAuth2UserInfo.getouath();
        String role = "USER";

		User loadUser = User.builder()
				.username(username1)
				.password(cosKey)
				.email(email1)
				.oauth(oauthType)
				.rule(role)
				.build();

		System.out.println(username1);
		System.out.println(cosKey);
		System.out.println(email1);
		System.out.println(role);
		/*
		 * System.out.println(ids); 
		 * System.out.println(Iids);
		 * System.out.println(IDtoInt);
		 */

		User originUser = userService.회원찾기(loadUser.getUsername());
		if (originUser.getUsername() == null) {
			System.out.println("기존 회원이 아닙니다 자동 회원가입을 진행합니다.");
			userService.회원가입(loadUser);
			/* UserService의 회원가입에 username, password, email 넣는곳 */

		}

		System.out.println("자동 로그인을 진행합니다");
		// 로그인 처리
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loadUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new PrincipalDetail(loadUser, oauth2User.getAttributes());
	}

	// 가입자 혹은 비가입자 체크 해서 처리

	/*
	 * @GetMapping("/test/oauth/login") public @ResponseBody String testOAuthLogin(
	 * Authentication authentication,
	 * 
	 * @AuthenticationPrincipal OAuth2User oauth) { System.out.println("");
	 * OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
	 * System.out.println("authentication:"+oauth2User.getAttributes());
	 * System.out.println("oauth2User:"+oauth.getAttributes()); return
	 * "OAuth 세션 정보 확인하기"; }
	 */

	/*
	 * @GetMapping("/test/login") public @ResponseBody String testLogin(
	 * Authentication authentication,
	 * 
	 * @AuthenticationPrincipal PrincipalDetail userDetails) {
	 * System.out.println(""); PrincipalDetail principalDetails = (PrincipalDetail)
	 * authentication.getPrincipal();
	 * System.out.println("authentication : "+principalDetails.getAttributes());
	 * System.out.println("userDetails :"+userDetails.getUsername());
	 * 
	 * System.out.println("userDetails : "+userDetails.getUser()); return
	 * "세션 정보 확인하기"; }
	 */

	@GetMapping("/user/updateForma")
	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetail principalDetails) {
		System.out.println("principalDetails : " + principalDetails.getName());
		return "user";
	}

}
