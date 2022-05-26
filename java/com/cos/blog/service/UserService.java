package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import ch.qos.logback.core.encoder.Encoder;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Transactional 
	public void 회원가입(User user) {
		    String rawPassword = user.getPassword();//1234 원문
		    String encPassword = encoder.encode(rawPassword);//해쉬됨
		    user.setPassword(encPassword);
		    user.setRole(RoleType.USER);
			userRepository.save(user);	
	}
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User(); /* 유저를 찾아서 없으면 빈 객체를 리턴해라 */
			
		});
				return user;
		
	}
	
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영소고하된 User 오브젝트를 수정
		// select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌..
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
// Validate 체크 db에 Oauth가 있고 없고를 감지해서 패스워드 수정가능여부 막기(다른프로그램을 이용한 공격 예방)
		if(persistance.getOauth() == null || persistance.getOauth().equals("")){
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		
	
		
			// 회원수정 함수 종료시 = 서비스 종료시 = 트랜잭션 종료 = commit 이 자동으로 된다.
		// 영속화된 persistance 객체의 변화가 감지되면 (더티체킹이 되어) update문을 날려줌.
	}
	
}
