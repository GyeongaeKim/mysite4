package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
	
	//필드
	@Autowired
	private UserService userService;
	
	//생성자
	
	//메소드 gs
	
	//메소드 일반
	//1. 회원등록폼
	@RequestMapping(value="/user/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController>joinForm()");
		return "user/joinForm";
	}
	
	//2. 회원등록
	@RequestMapping(value="/user/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("UserController>join()");
		userService.userInsert(userVo);
		return "user/joinOk";
	}
	
	
	//3. 로그인폼
	@RequestMapping(value="/user/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController>loginForm()");
		return "user/loginForm";
	}
	
	
	//4. 로그인
	@RequestMapping(value="/user/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo) {
		System.out.println("UserController>login()");
		userService.getUser(userVo);
		return "user/loginForm";
	}
	//5. 로그아웃
	//6. 수정폼
	//7. 수정
}
