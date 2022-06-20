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
		return sqlSession.insert("user.userInsert", userVo);
	}
	
	//2. 로그인
	public UserVo getUser (UserVo userVo) {
		System.out.println("UserDao>getUser()");
		UserVo authVo = sqlSession.selectOne("user.getUser", userVo);
		System.out.println("Dao " + authVo);
		return authVo;
	}

	
	//3. 로그아웃
	//4. 회원정보 가져오기(수정)
	public UserVo getModifyUser (int no) {
		System.out.println("UserDao>getModifyUser()");
		return sqlSession.selectOne("user.getModifyUser", no);
	}
	
	//5. 수정
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao>userUpdate()");
		return sqlSession.update("user.userUpdate", userVo);
	}
	
}
