package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RBoardService;
import com.javaex.vo.RBoardVo;
import com.javaex.vo.UserVo;

@Controller
public class RBoardController {
	
	//필드
	@Autowired
	private RBoardService rBoardService;
	
	
	//1. r게시판 리스트 list
	@RequestMapping(value="/rboard/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String rBoardList(Model model, String keyword) {
		System.out.println("RBoardController>rBoardList()");
		
		if(keyword==null) {
			keyword ="";
		}
		keyword = "%" + keyword + "%";
		
		List<RBoardVo> rBoardList = rBoardService.rBoardList(keyword);
		model.addAttribute("rBoardList", rBoardList);
		return "rboard/list";
	}
	
	
	
	
	//2. 글쓰기폼
	@RequestMapping(value="/rboard/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("RBoardController>writeForm()");
		return "rboard/writeForm";
	}
	
	//3. 글쓰기 insert
	@RequestMapping(value="/rboard/insert", method = {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute RBoardVo rBoardVo, HttpSession session) {
		System.out.println("RBoardController>insert()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		rBoardVo.setUserNo(userNo);
		
		rBoardService.boardInsert(rBoardVo);
		return "redirect:/rboard/list";
	}
	
	
	
	//4. r게시글 읽기
	@RequestMapping(value="/rboard/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @RequestParam("no")int no) {
		System.out.println("RBoardController>read()");
		
		Map<String, Object> rboardMap = rBoardService.readBoard(no);
		model.addAttribute("rboardMap", rboardMap);
		
		return "rboard/read";
	}
	
	
	
	
	
	//5. 수정폼
	@RequestMapping(value="/rboard/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("RBoardController>modifyForm()");
		Map<String, Object> rboardMap = rBoardService.getBoard(no);
		model.addAttribute("rboardMap", rboardMap);
		return "rboard/modifyForm";
	}
	
	//6. 수정
	@RequestMapping(value="/rboard/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute RBoardVo rBoardVo) {
		System.out.println("RBoardController>modify()");
		rBoardService.boardUpdate(rBoardVo);
		return "redirect:/rboard/list";
	}
	
	
	
	
	
	
	//7. 삭제
	@RequestMapping(value="/rboard/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("RBoardController>boardDelete()");
		rBoardService.boardDelete(no);
		return "redirect:/rboard/list";
	}
	
}
