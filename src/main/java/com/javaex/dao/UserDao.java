package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;


@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//1.회원등록
	public int userInsert(UserVo userVo) {
		System.out.println("UserDao>userInsert()");
		return sqlSession.insert("mysite.userInsert", userVo);
	}
	
	//2. 로그인
	public UserVo getUser (UserVo userVo) {
		System.out.println("UserDao>getUser()");
		return sqlSession.selectOne("mysite.getUser", userVo);
	}

	
	//3. 로그아웃
	//4. 회원정보 가져오기(수정)
	//5. 수정
}
