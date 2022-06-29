package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	//필드
	@Autowired
	private GuestbookDao guestbookDao;
	
	//생성자
	//메소드 gs
	
	//메소드 일반
	//1. 방명록 리스트 addList
	public List<GuestbookVo> guestList() {
		System.out.println("GuestbookService>guestList()");
		List<GuestbookVo> guestList =  guestbookDao.guestList();
		return guestList;
	}
	
	//2. 방명록 추가 insert
	public int guestInsert(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService>guestInsert()");
		return guestbookDao.guestInsert(guestbookVo);
	}
	
	//3. 삭제 delete
	public int guestDelete(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService>guestDelete()");
		return guestbookDao.guestDelete(guestbookVo);
	}
	
	
	
	
	
	
	//ajax활용 - 전체리스트 가져오기
	public List<GuestbookVo> getGuestList() {
		System.out.println("GuestbookService>getGuestList()");
		List<GuestbookVo> getGuestList =  guestbookDao.selectList();
		return getGuestList;
	}
	
	//ajax활용 - 방명록 저장
	public GuestbookVo addGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService>getGuestList()");
		
		System.out.println("전->" + guestbookVo);
		int count = guestbookDao.insertGuest(guestbookVo);
		System.out.println("후->" + guestbookVo);
		
		int no = guestbookVo.getNo();
		
		//방금 저장한 1개의 데이터를 가져온다.
		GuestbookVo gVo = guestbookDao.getGuest(no);
		
		return gVo;
	}
	
	//ajax활용 - 방명록 삭제
	public String removeGuest(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService>removeGuest()");
		
		String state;
		int count = guestbookDao.deleteGuest(guestbookVo);
		
		if(count>0) {
			state = "success";
		}else {
			state = "fail";
		}
		
		return state;
	}
	
	
	
	
	
	
	
}
