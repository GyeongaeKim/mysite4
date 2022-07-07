package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
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
	
	public List<Map<String, Object>> getList(){
		List<Map<String, Object>> gList = galleryDao.getList();
		return gList;
	}
	
	
	
	
	public int upload(MultipartFile file, GalleryVo gVo) {
		
		String orgName = file.getOriginalFilename();
		gVo.setOrgName(orgName);
		
		String exName = orgName.substring(orgName.lastIndexOf("."));
		long rand1 = System.currentTimeMillis();
		String rand2 = UUID.randomUUID().toString();
		String saveName = rand1 + rand2 + exName;
		gVo.setSaveName(saveName);
		
		String saveDir = "\\C:\\javastudy\\upload";
		String filePath = saveDir + "\\" + saveName;
		gVo.setFilePath(filePath);
		
		long fileSize = file.getSize();
		gVo.setFileSize(fileSize);
		
		System.out.println(gVo);
		int count = galleryDao.add(gVo);
		
		if(count > 0) {
			try {
				byte[] fileData = file.getBytes();
				OutputStream os = new FileOutputStream(filePath);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				bos.write(fileData);
				bos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	public GalleryVo getGallery(int no) {
		GalleryVo gVo = galleryDao.getGallery(no);
		return gVo;
	}
	
	public int delete(int no) {
		int count = galleryDao.delete(no);
		return count;
	}
	
	
	
}
