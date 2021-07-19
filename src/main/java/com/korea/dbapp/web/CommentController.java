package com.korea.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.korea.dbapp.domain.comment.Comment;
import com.korea.dbapp.domain.comment.CommentRepository;
import com.korea.dbapp.domain.user.User;

import lombok.Data;

@Data
@Controller
public class CommentController {
	
	private final CommentRepository commentRepository;
	private final HttpSession session;
	
	
	// 1. sava - Post - Data Return
	
	
	// 2. delete - Delete - Data Return
	@DeleteMapping("/comment/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) {
		User pricipal = (User) session.getAttribute("principal");
		int userId =pricipal.getId();
		Comment commentEntity =  commentRepository.findById(id).get();
		int commentUserId = commentEntity.getUser().getId();
		
		if(userId == commentUserId) {
			commentRepository.deleteById(id);
			return "ok";
		}else {
			return "fail"; 
		}
		
	}
	
	// 3. findAllByPostId - Get 
}
