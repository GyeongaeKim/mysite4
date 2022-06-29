package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//1. 방명록 리스트 addList
	public List<GuestbookVo> guestList() {
		System.out.println("GuestbookDao>guestList()");
		return sqlSession.selectList("guestbook.guestList");
	}
	
	
	//2. 방명록 추가 insert
	public int guestInsert(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao>guestInsert()");
		return sqlSession.insert("guestbook.guestInsert", guestbookVo);
	}
	
	
	//3. 삭제 delete
	public int guestDelete(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao>guestDelete()");
		return sqlSession.delete("guestbook.guestDelete", guestbookVo);
	}
	
	
	
	
	
	
	
	
	
	
	//ajax활용 - 전체리스트 가져오기
	public List<GuestbookVo> selectList() {
		System.out.println("GuestbookDao>selectList()");
		return sqlSession.selectList("guestbook.selectList");
	}
	
	
	//ajax활용 - 방명록 저장
	public int insertGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao>insertGuest()");
		
		System.out.println("xml실행 전->" + guestbookVo);
		
		int count =  sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		System.out.println("xml실행 후->" + guestbookVo);
		
		return count;
	}
	
	
	//방명록 저장후 등록한 데이터 가져오기(ajax활용)
	public GuestbookVo getGuest(int no) {
		System.out.println("GuestbookDao>getGuest()");
		
		GuestbookVo guestbookVo = sqlSession.selectOne("guestbook.getGuest", no);
		
		return guestbookVo;
	}
	
	
	
	//ajax활용 - 방명록 삭제
	public int deleteGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao>deleteGuest()");
		return sqlSession.delete("guestbook.deleteGuest", guestbookVo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
