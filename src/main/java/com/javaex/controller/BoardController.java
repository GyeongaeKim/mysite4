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
	
	
	
	//리스트(일반)
	@RequestMapping(value="/board/list4", method = {RequestMethod.GET, RequestMethod.POST})
	public String list4(Model model,	//*defaultValue->값이 없을때 대체할 값 
					@RequestParam(value="crtPage", required=false, defaultValue="1") 
					int crtPage) {
		System.out.println("BoardController>list4()");
		
		Map<String, Object> pMap = boardService.getBoardList4(crtPage);
		model.addAttribute("pMap", pMap);
		
		return "board/list4";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//메소드 일반
	//1. 게시판 리스트 list
	@RequestMapping(value="/board/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String boardList(Model model, String keyword) {
		System.out.println("BoardController>boardList()");
		
		if(keyword == null) {
			keyword = "";
		}
		keyword = "%" + keyword + "%";
		
		List<BoardVo> boardList = boardService.boardList(keyword);
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	
	///수업시간 코드//////////////////////////////////////////
	//리스트(일반 + 검색)
	@RequestMapping(value = "/board/list3", method = { RequestMethod.GET, RequestMethod.POST })
	public String list3(Model model, 
						@RequestParam(value="keyword", required=false, defaultValue="") String keyword) {
		System.out.println("BoardContoller>list3()");

		List<BoardVo> boardList = boardService.getBoardList3(keyword);	
		model.addAttribute("boardList", boardList);
		
		return "board/list3";
	}
	
	
	// 리스트(리스트만 출력할때)
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("BoardContoller>list()");

		List<BoardVo> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList", boardList);
		return "board/list";
	}

	
	
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(@RequestParam("keyword") String keyword, Model model  ) {
		System.out.println("BoardContoller>search()");
		
		List<BoardVo> boardList = boardService.getBoardList2(keyword);
		model.addAttribute("boardList", boardList);
		
		return "board/list2";
	}
	
	////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	
	
	
	
	
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
