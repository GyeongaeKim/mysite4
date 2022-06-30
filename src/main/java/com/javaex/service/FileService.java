package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.FileVo;

@Service
public class FileService {
	
	//파일을 하드디스크에 저장 + 파일 정보(db저장) 추출 저장
	public String save(MultipartFile file) {
		System.out.println("FileService>save()");
		
		String saveDir = "c:\\javaStudy\\upload";
		
		
		/*
		String test = UUID.randomUUID().toString();		//UUID는 String형이 아니기때문에 .toString() 붙여줘야한다
		long test2 = System.currentTimeMillis();
		
		System.out.println(test);
		System.out.println(test2);
		*/
		
		
		
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
		FileVo fileVo = new FileVo(orgName, saveName, filePath, fileSize);
		System.out.println(fileVo);
		
		//--> Dao; db저장
		
		
		
		
		
		//2.파일저장-하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream os = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			
			bos.write(fileData);
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
	}
}
