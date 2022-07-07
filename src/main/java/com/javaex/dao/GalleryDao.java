package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	//갤러리 리스트
	public List<Map<String, Object>> getList() {
		List<Map<String, Object>> gList = sqlSession.selectList("gallery.getList");
		System.out.println(gList);
		return gList;
	}
	
	//이미지 저장
	public int add(GalleryVo gVo) {
		int count = sqlSession.insert("gallery.insert", gVo);
		return count;
	}
	
	
	//getGallery
	public GalleryVo getGallery(int no) {
		GalleryVo gVo = sqlSession.selectOne("gallery.getGallery", no);
		return gVo;
	}
	
	//삭제
	public int delete(int no) {
		int count = sqlSession.delete("gallery.delete", no);
		return count;
	}
	
}
