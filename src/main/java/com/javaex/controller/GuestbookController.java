package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

@Controller
public class GuestbookController {
	
	//필드
	@Autowired
	private GuestbookService guestbookService;
	
	//생성자
	//메소드gs
	
	//메소드 일반
	//1. 방명록 리스트 addList
	@RequestMapping(value="/guestbook/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestbookController>addList()");
		List<GuestbookVo> guestList = guestbookService.guestList();
		model.addAttribute("guestList", guestList);
		return "guestbook/addList";
	}
	
	
	//2. 방명록 등록 insert
	@RequestMapping(value="/guestbook/insert", method = {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController>insert()");
		System.out.println(guestbookVo);
		
		guestbookService.guestInsert(guestbookVo);
		return "redirect:/guestbook/addList";
	}
	
	
	//3. 삭제폼 deleteForm
	@RequestMapping(value="/guestbook/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("GuestbookController>deleteForm()");
		return "guestbook/deleteForm";
	}
	
	
	//delete
	@RequestMapping(value="/guestbook/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController>delete()");
		System.out.println(guestbookVo);
		
		guestbookService.guestDelete(guestbookVo);
		return "redirect:/guestbook/addList";
	}
	
	
}
