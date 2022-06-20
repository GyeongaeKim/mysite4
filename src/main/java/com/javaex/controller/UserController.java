package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		System.out.println(userVo);
		
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
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>login()");
		
		UserVo authUser = userService.userLogin(userVo);
		
		//세션에 저장!
		if(authUser != null) {	//로그인 성공
			System.out.println("로그인 성공!!");
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		}else {
			System.out.println("로그인 실패ㅠㅠ");
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	
	//5. 로그아웃
	@RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>logout()");
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
	//6. 수정폼
	@RequestMapping(value="/user/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>modifyForm()");
		
		
		
		//로그인한 사용자의 no값을 세션에서 가져오기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		System.out.println(no);
		
		//no로 사용자 정보 가져오기
		userVo = userService.getModifyUser(no);
		System.out.println(userVo);
		
		model.addAttribute("userVo", userVo);
		return "user/modifyForm";
	}
	
	
	//7. 수정
	@RequestMapping(value="/user/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController>modify()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int no = authUser.getNo();
		
		userVo.setNo(no);
		
		userService.userUpdate(userVo);
		return "redirect:/main";
	}
	
	
}
