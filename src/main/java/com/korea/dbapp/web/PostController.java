package com.korea.dbapp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.comment.Comment;
import com.korea.dbapp.domain.comment.CommentRepository;
import com.korea.dbapp.domain.post.Post;
import com.korea.dbapp.domain.post.PostRepository;
import com.korea.dbapp.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	private final PostRepository postRepository;
	private final HttpSession session;
	private final HttpServletRequest request;
	private final CommentRepository commentRepository;

	@GetMapping({ "/", "/post" })
	public String list(Model model) { 
		model.addAttribute("postsEntity", postRepository.findAll());
		return "post/list";
	}
 
	@GetMapping("/post/{id}")
	public String detail(@PathVariable int id, Model model) {
		Post postEntity = postRepository.findById(id).get();
		model.addAttribute("postEntity", postEntity);
		
		List<Comment> commentsEntity = commentRepository.mFindAllByPostId(id);
		model.addAttribute("commentsEntity", commentsEntity);
		return "post/detail";
	}
 
	@DeleteMapping("/post/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) {
		// 1. 권환체크 ( post id를 통해 user id 를 찾아서 session의 id를 비교 ) - 생략

		// 세션의 user id 찾기 (session)
		User principal = (User) session.getAttribute("principal");
		int userId = principal.getId();
   
		// post의 user id 찾기 ({id})
		Post postEntity = postRepository.findById(id).get();
		int postUserId = postEntity.getUser().getId();

		// 2. {id} 값으로 삭제!!!! 
		if (userId == postUserId) {
			postRepository.deleteById(id);
			return "ok"; 
		} else {
			return "fail";
		} 
	} // end delete

	@GetMapping("/post/saveForm")
	public String saveForm() {
		// 1. 인증 체크 - > 숙제

		return "post/saveForm";
	}
 
	@PostMapping("/post")
	public String save(Post post) {
		User principal = (User) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/auth/loginForm";
		}

		post.setUser(principal);
		postRepository.save(post);
		return "redirect:/";
	}
 
	@GetMapping("/post/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		User principal = (User) session.getAttribute("principal");
		int loginId = principal.getId();

		Post postEntity = postRepository.findById(id).get();
		int postOwnerId = postEntity.getUser().getId();

		if (loginId == postOwnerId) {
			model.addAttribute("postEntity", postEntity);
			postRepository.save(postEntity);
			return "post/updateForm";
		} else {
			return "redirect:/auth/loginForm";
		} // end if
	}

	@PutMapping("/post/{id}")
	public @ResponseBody String update(@PathVariable int id, @RequestBody Post post) {
		Post postEntity = postRepository.findById(id).get();
		postEntity.setTitle(post.getTitle());
		postEntity.setContent(post.getContent());
		postRepository.save(postEntity);
		return "ok";
	}
}
