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

}
