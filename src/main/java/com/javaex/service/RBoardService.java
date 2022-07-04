package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Service
public class RBoardService {
	
	//필드
	@Autowired
	private RBoardDao rBoardDao;
	
	
	//1.r게시판 리스트
	public List<RBoardVo> rBoardList(String keyword){
		System.out.println("RBoardService>rBoardList()");
		List<RBoardVo> rBoardList = rBoardDao.rBoardList(keyword);
		return rBoardList;
	}
	
	
	
	//2. 글쓰기 insert 
	public int boardInsert(RBoardVo rBoardVo) {
		System.out.println("RBoardService>boardInsert()");
		return rBoardDao.boardInsert(rBoardVo);
	}
	
	
	
	
	
	
	//3. 조회수+읽기
	public Map<String, Object> readBoard(int no){
		System.out.println("RBoardService>readBoard()");
		
		rBoardDao.updateHit(no);
		Map<String, Object> rboardMap = rBoardDao.readBoard(no);
		
		return rboardMap;
	}
	
	
	
	//4. 수정폼-게시글 가져오기
	public Map<String, Object> getBoard(int no) {
		System.out.println("RBoardService>getBoard()");
		Map<String, Object> rboardMap = rBoardDao.getBoard(no);
		return rboardMap;
	}
	
	
	//5. 수정
	public int boardUpdate(RBoardVo rBoardVo) {
		System.out.println("RBoardService>boardUpdate()");
		return rBoardDao.boardUpdate(rBoardVo);
	}
	
	
	
	//6. 삭제
	public int boardDelete(int no) {
		System.out.println("RBoardService>boardDelete()");
		return rBoardDao.boardDelete(no);
	}
	
	
	
	
	
	//7. 댓글쓰기 
	public int replyInsert(RBoardVo rBoardVo) {
		System.out.println("RBoardService>replyInsert()");
		return rBoardDao.replyInsert(rBoardVo);
	}
	
	
	
	
	
}
