package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//리스트4
	public List<BoardVo> selectList4(int startRnum, int endRnum){
		System.out.println("BoardDao>selectList4()");
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		System.out.println(map);
		List<BoardVo> boardList = sqlSession.selectList("board.selectList4", map);
		
		return boardList;
	}
	
	
	
	
	
	//전체글 갯수(페이징)
	public int selectTotalCnt() {
		System.out.println("BoardDao>selectTotalCnt()");
		
		int totalCnt = sqlSession.selectOne("board.selectTotalCnt");
		
		return totalCnt;
	}
	
	
	
	
	
	//1. 게시판 리스트 list
	public List<BoardVo> boardList(String keyword){
		System.out.println("BoardDao>boardList()");
		return sqlSession.selectList("board.boardList", keyword);
	}
	
	
	
	
	///수업시간 코드/////////////////////////////////////////////////////////
	public List<BoardVo> selectList3(String keyword) {
		System.out.println("BoardDao>selectList3()");
		List<BoardVo> boardList = sqlSession.selectList("board.selectList3", keyword);
		return boardList;
	}
	
	// 글전체 가져오기(리스트만 출력할때)
	public List<BoardVo> selectList() {
		System.out.println("BoardDao>selectList()");
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		return boardList;
	}
	
	public List<BoardVo> selectList2(String keyword){
		System.out.println("BoardDao>selectList2()");
		System.out.println(keyword);
		List<BoardVo> boardList = sqlSession.selectList("board.selectList2", keyword);
		return boardList;
	}
	
	
	/////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	//2. 게시판 검색
	//3. 삭제
	public int boardDelete(int no) {
		System.out.println("BoardDao>boardDelete()");
		return sqlSession.delete("board.boardDelete", no);
	}
	
	
	//4. 읽기 -조회수+
	public int updateHit(int no) {
		System.out.println("BoardDao>updateHit()");
		return sqlSession.update("board.updateHit", no);
	}
	//4. 게시글 읽기
	public Map<String, Object> readBoard(int no) {
		System.out.println("BoardDao>readBoard()");
		Map<String, Object> boardMap = sqlSession.selectOne("board.readBoard", no);
		return boardMap;
	}
	
	
	//5. 수정폼(게시글 가져오기)
	public Map<String, Object> getBoard(int no) {
		System.out.println("BoardDao>getBoard()");
		Map<String, Object> boardMap = sqlSession.selectOne("board.getBoard", no);
		return boardMap;
	}
	//6. 수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("BoardDao>boardUpdate()");
		return sqlSession.update("board.boardUpdate", boardVo);
	}
	
	
	//7. 글쓰기 insert
	public int boardInsert(BoardVo boardVo) {
		System.out.println("BoardDao>boardInsert()");
		return sqlSession.insert("board.boardInsert", boardVo);
	}
}
