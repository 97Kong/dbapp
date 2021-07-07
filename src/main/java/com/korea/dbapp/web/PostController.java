package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.korea.dbapp.domain.post.PostRepository;

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
}
