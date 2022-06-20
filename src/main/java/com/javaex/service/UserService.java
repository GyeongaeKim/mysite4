package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	//필드
	@Autowired
	private UserDao userDao;
	
	
	//생성자
	//메소드 gs
	//메소드 일반
	//1.회원등록
	public int userInsert(UserVo userVo) {
		System.out.println("UserService>userInsert()");
		int count = userDao.userInsert(userVo);
		return count;
	}
	
	//2. 로그인
	public UserVo userLogin(UserVo userVo) {
		System.out.println("UserService>userLogin()");
		UserVo authUser = userDao.getUser(userVo);
		
		
		return authUser;
	}
	
	//3. 로그아웃
	//4. 회원정보 가져오기(수정)
	public UserVo getModifyUser(int no) {
		System.out.println("UserService>getModifyUser()");
		return  userDao.getModifyUser(no);
	}
	
	//5. 수정
	public int userUpdate(UserVo userVo) {
		System.out.println("UserService>userUpdate()");
		return  userDao.userUpdate(userVo);
	}
	
}
