package com.korea.dbapp.test;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.domain.user.UserRepository;

@RestController
public class UserApiControllerTest {
	
	private final UserRepository userRepository;

	public UserApiControllerTest(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@PostMapping("/test/user")
	public String save(User user) {
		userRepository.save(user);
		return "save ok";
	}
	
	@GetMapping("/test/user")
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	// http://localhost:8000/user/2
	@GetMapping("/test/user/{id}")
	public String findById(@PathVariable int id) {
		User userEntity = userRepository.findById(0).get();/*무조건 있어라는 뜻의 get*/
		System.out.println(userEntity.toString());
		return "ok";		
	}
	
	@GetMapping("/test/user/username/{username}")
	public User findByUsername(@PathVariable String username) {
		return userRepository.mfindByUsername(username);
	}
	
	@PostMapping("/test/login")
	public String login(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		user = userRepository.mLogin(username, password);
		if(user == null) {
			return "login fail";
		} else {
			return "login success";
		}
	}
	
	@DeleteMapping("/test/user/{id}")
	public String deleteById(@PathVariable int id) {
		userRepository.deleteById(id);
		return "delete ok";
	}
	
	@PutMapping("/test/user/{id}")
	public String updateOne(@PathVariable int id, User user) { // password, email
		// 1. id로 select 하기
		User userEntity = userRepository.findById(id).get();
		
		// 2. 받은 body 데이터로 수정하기
		userEntity.setPassword(user.getPassword());
		userEntity.setEmail(user.getEmail());
		
		// 3. save하면 된다. (이떄 꼭 id값이 db에 존재해야 update가 된다)
		userRepository.save(userEntity);
		
		return "update ok";
	}
	
	@PostMapping("/test/juso")
	public String jusoTest(String user) {
		return user;
	}
}
