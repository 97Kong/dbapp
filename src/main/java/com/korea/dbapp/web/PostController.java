package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.post.PostRepository;
import com.korea.dbapp.domain.user.User;
import com.korea.dbapp.util.Script;

@Controller
public class PostController {
	private final PostRepository postRepository;
	private final HttpSession session;

	public PostController(PostRepository postRepository, HttpSession session) { // 디펜더쉽 인젝션?
		super();
		this.postRepository = postRepository;
		this.session = session;
	}

	@GetMapping({"/", "/post"})
	public String list(Model model) {
		model.addAttribute("postsEntity", postRepository.findAll());
		return "post/list";
	}
	
	@GetMapping("/posts/{id}")
	public String detail(@PathVariable int id, Model model) {
		Post postEntity = postRepository.findById(id).get();
		model.addAttribute("postEntity", postEntity);
		return "post/detail";
	}
	
	@PostMapping("/post/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) {
		// 1. 권환체크 ( post id를 통해 user id 를 찾아서 session의 id를 비교 ) - 생략
		
		// 세션의 user id 찾기 (session)
		User principal = (User) session.getAttribute("principal");
		int userId = principal.getId();
		
		// post의 user id 찾기 ({id})
		Post postEntity = postRepository.findById(id).get();
		int postUserId = postEntity.getUser().getId();
		
		
		// 2. {id} 값으로 삭제!!!!
		if(userId == postUserId) {
			postRepository.deleteById(id);
			return Script.href("/", "삭제 완료");
		} else {
			return Script.back("삭제 실패");
		}
	} // end delete
	
	@GetMapping("/post/saveForm")
	public String saveForm() {
		// 1. 인증 체크 - > 숙제
		
		return "post/saveForm";
	}
}
