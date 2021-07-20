package com.korea.dbapp.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public String test1(Exception e, Model model) {
		model.addAttribute("msg", e.getMessage());
		return "error";
	}
}
