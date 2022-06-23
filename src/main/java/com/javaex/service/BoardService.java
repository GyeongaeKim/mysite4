package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	//필드
	@Autowired
	private BoardDao boardDao;
	
	//생성자
	//메소드 gs
	
	//메소드 일반
	//1. 게시판 리스트 list
	public List<BoardVo> boardList(String keyword){
		System.out.println("BoardService>boardList()");
		List<BoardVo> boardList = boardDao.boardList(keyword);
		return boardList;		
	}
	
	
	
	
	//수업시간 코드///////////////////////////////////////////
	public List<BoardVo> getBoardList3(String keyword) {
		System.out.println("BoardService>getBoardList3()");
		List<BoardVo> boardList = boardDao.selectList3(keyword);	
		return boardList;
	}
	
	// 리스트(리스트만 출력할때)
	public List<BoardVo> getBoardList() {
		System.out.println("BoardService>getBoardList()");
		List<BoardVo> boardList = boardDao.selectList();	
		return boardList;
	}
	public List<BoardVo> getBoardList2(String keyword){
		System.out.println("BoardService>getBoardList2()");
		List<BoardVo> boardList = boardDao.selectList2(keyword);
		return boardList;
	}
	
	
	/////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	//2. 게시판 검색
	//3. 삭제
	public int boardDelete(int no) {
		System.out.println("BoardService>boardDelete()");
		return boardDao.boardDelete(no);
	}
	
	
	
	//4. 읽기 -조회수+
	public Map<String, Object> readBoard(int no) {
		System.out.println("BoardService>readBoard()");
		
		boardDao.updateHit(no);
		Map<String, Object> boardMap = boardDao.readBoard(no);
		
		return boardMap;
	}
	
	
	//5. 수정폼(게시글 가져오기)
	public Map<String, Object> getBoard(int no) {
		System.out.println("BoardService>getBoard()");
		Map<String, Object> boardMap = boardDao.getBoard(no);
		return boardMap;
	}
	//6. 수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("BoardService>boardUpdate()");
		return boardDao.boardUpdate(boardVo);
	}
	
	
	//7. 글쓰기 insert
	public int boardInsert(BoardVo boardVo) {
		System.out.println("BoardService>boardInsert()");
		return boardDao.boardInsert(boardVo);
	}
	
	
	
}
