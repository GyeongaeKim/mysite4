package com.javaex.dao;

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
	
	//1. 게시판 리스트 list
	public List<BoardVo> boardList(String keyword){
		System.out.println("BoardDao>boardList()");
		return sqlSession.selectList("board.boardList", keyword);
	}
	
	
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
