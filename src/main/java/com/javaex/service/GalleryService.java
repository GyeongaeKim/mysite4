package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao;
	
	public List<GalleryVo> galleryList(){
		System.out.println("GalleryService>galleryList()");
		return galleryDao.galleryList();
	}
	
	
	
	
	public int saveImg(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("GalleryService>saveImg()");
		
		String saveDir = "c:\\javaStudy\\upload";
		
		//1.파일 정보(db저장) 추출 저장
		//1-오리지날 파일명
		String orgName = file.getOriginalFilename();
		
		//2-확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));	//확장자명 확인
		//System.out.println(exName);
		
		//3-저장파일명(+확장자명)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		//4-파일경로(디렉토리+저장파일명)
		String filePath = saveDir + "\\" + saveName;
		
		//5-파일사이즈
		long fileSize = file.getSize();
				
				
		
		//***필요한 정보들을 Vo로 묶기
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);
		
		System.out.println(galleryVo);
		
		
		
		//*.파일저장-하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return galleryDao.saveImg(galleryVo);
	}
	
	
	
}
