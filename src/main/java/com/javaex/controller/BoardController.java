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

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
public class BoardController {
	
	//필드
	@Autowired
	private BoardService boardService;
	
	//생성자
	//메소드gs
	
	//메소드 일반
	//1. 게시판 리스트 list
	@RequestMapping(value="/board/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardList(Model model, String keyword) {
		System.out.println("BoardController>boardList()");
		List<BoardVo> boardList = boardService.boardList(keyword);
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	
	
	//2. 게시판 검색
	//3. 삭제
	@RequestMapping(value="/board/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("BoardController>boardDelete()");
		boardService.boardDelete(no);
		return "redirect:/board/list";
	}
	
	
	//4. 읽기(조회수+)
	@RequestMapping(value="/board/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("BoardController>read()");
		
		//조회수먼저 올리고 게시글을 불러와야한다
		boardService.updateHit(no);
		Map<String, Object> boardMap = boardService.readBoard(no);
		
		model.addAttribute("boardMap", boardMap);
		return "board/read";
	}
	
	
	//5. 수정폼
	@RequestMapping(value="/board/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, @RequestParam("no") int no) {
		System.out.println("BoardController>modifyForm()");
		Map<String, Object> boardMap = boardService.getBoard(no);
		model.addAttribute("boardMap", boardMap);
		return "board/modifyForm";
	}
	//6. 수정***
	@RequestMapping(value="/board/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("BoardController>modify()");
		boardService.boardUpdate(boardVo);
		return "redirect:/board/list";
	}
	
	
	//7. 글쓰기폼
	@RequestMapping(value="/board/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("BoardController>writeForm()");
		return "board/writeForm";
	}
	//8. 글쓰기 insert
	@RequestMapping(value="/board/insert", method = {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("BoardController>insert()");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		boardVo.setUserNo(userNo);
		
		boardService.boardInsert(boardVo);
		return "redirect:/board/list";
	}
	
	
	
}
