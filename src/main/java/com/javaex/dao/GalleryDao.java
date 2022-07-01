package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	//갤러리 리스트
	public List<GalleryVo> galleryList(){
		System.out.println("GalleryDao>galleryList()");
		return sqlSession.selectList("gallery.galleryList");
	}
	
	//이미지 저장
	public int saveImg(GalleryVo galleryVo) {
		System.out.println("GalleryDao>saveImg()");
		return sqlSession.insert("gallery.saveImg", galleryVo);
	}
	
}
