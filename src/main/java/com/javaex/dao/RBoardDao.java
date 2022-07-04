package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RBoardVo;

@Repository
public class RBoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//1.r게시판 리스트 list
	public List<RBoardVo> rBoardList(String keyword){
		System.out.println("RBoardDao>rBoardList()");
		return sqlSession.selectList("rboard.rBoardList", keyword);
	}
	
	
	
	
	
	
	
	//2. 글쓰기 insert
	public int boardInsert(RBoardVo rBoardVo) {
		System.out.println("RBoardDao>boardInsert()");
		return sqlSession.insert("rboard.boardInsert", rBoardVo);
	}
	
	
	
	
	
	
	
	
	//3. 조회수 +
	public int updateHit(int no) {
		System.out.println("RBoardDao>updateHit()");
		return sqlSession.update("rboard.updateHit", no);
	}
	
	//4. 게시글 읽기
	public Map<String, Object> readBoard(int no){
		System.out.println("RBoardDao>readBoard()");
		Map<String, Object> rboardMap = sqlSession.selectOne("rboard.readBoard", no);
		return rboardMap;
	}
	
	
	
	
	
	//5. 수정폼(게시글 가져오기)
	public Map<String, Object> getBoard(int no) {
		System.out.println("RBoardDao>getBoard()");
		Map<String, Object> rboardMap = sqlSession.selectOne("rboard.getBoard", no);
		return rboardMap;
	}
	//6. 수정
	public int boardUpdate(RBoardVo rBoardVo) {
		System.out.println("RBoardDao>boardUpdate()");
		return sqlSession.update("rboard.boardUpdate", rBoardVo);
	}
	
	
	
	//7. 삭제
	public int boardDelete(int no) {
		System.out.println("RBoardDao>boardDelete()");
		return sqlSession.delete("rboard.boardDelete", no);
	}
	
	
	
	
	
	
	//8. 댓글쓰기 insert
	public int replyInsert(RBoardVo rBoardVo) {
		System.out.println("RBoardDao>replyInsert()");
		return sqlSession.insert("rboard.replyInsert", rBoardVo);
	}
	
	
	
	
}
