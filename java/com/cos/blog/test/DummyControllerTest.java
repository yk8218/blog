package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;



@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 함.
	// email, password
	
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
     try {
		userRepository.deleteById(id);
     } catch (EmptyResultDataAccessException e) {
    	 return "삭제에 실패하였습니다. 해당 id는 DB에 없음";
     }
		return "삭제되었습니다. id:"+id;
	}
	
	//@Transactional 를걸면 save안해도 업데이트가 된다
	@Transactional // 함수 종료시에 자동 commit이됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) { //json 데이터를 요청 => java object (messageConverter의 jackson 라이브러리가 변환해서 받아줌
		System.out.println("id:"+id);
		System.out.println("password:"+requestUser.getPassword());
	    System.out.println("email"+requestUser.getEmail());
//findById이아이디를 찾을수없으면 orElseThrow 특정함수를 사용해라 28강 10:18
	    
	    User user = userRepository.findById(id).orElseThrow(()->{
	    	return new IllegalArgumentException("수정에 실패하였습니다.");
	    });
	    user.setPassword(requestUser.getPassword());
	    user.setEmail(requestUser.getEmail());
	    
	    
	   // userRepository.save(user);
	    
	   // 더티 체킹
	    return user;
	}
	//△28강 13:02 (아마도 회원수정?)
	
	// http://localhost:8000/blog/dummy/users
	// 다들고오는애
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
		
	}
	
	// 한페이지당 2건에 데이터 리턴 (페이징한다는뜻)
	// http://localhost:8000/blog/dummy/user?page=0
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction= Sort.Direction.DESC)Pageable pageable){
		Page<User> pagingUsers = userRepository.findAll(pageable);
	    
		List<User> users = pagingUsers.getContent();
		return pagingUsers;
	}
	
	// {id} 주소로 파라레터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이됨
		// 그럼 return null 이 리턴이됨 그럼 프로그램에 문제가있음
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해라
		
		
		//↓ 26강 15:40쯤
		User user =userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
			}
		});
		//변환 (웹브라우저 이해하는 데이터) → json (Gson라이브러리)
		return user;
	}

	//http://localhost:8000/blog/dummy/join
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value(약속된 규칙)
	  System.out.println("username"+user.getUsername());
	  System.out.println("password:"+user.getPassword());
	  System.out.println("email:"+user.getEmail());
	  System.out.println("role :"+user.getRole());
	  System.out.println("createDate:"+user.getCreateDate());
	
	  user.setRole(RoleType.USER);
	  userRepository.save(user);
	    return "회원가입이 완료되었습니다.";
	}
}
