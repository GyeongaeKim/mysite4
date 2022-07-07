package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;


@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	
	
	//갤러리 리스트
	@RequestMapping(value="/gallery/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("GalleryController>list");
		
		List<Map<String, Object>> gList = galleryService.getList();
		model.addAttribute("gList", gList);
		return "gallery/list";
	}
	
	
	//이미지 올리기
	@RequestMapping(value="/gallery/upload", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file, @RequestParam("content") String content, HttpSession session) {
		System.out.println("GalleryController: add");
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int userNo = authUser.getNo();
		
		GalleryVo gVo = new GalleryVo();
		gVo.setUserNo(userNo);
		gVo.setContent(content);
		System.out.println(gVo);
		
		int count = galleryService.upload(file, gVo);
		
		return "redirect:/gallery/list";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/getGallery", method= {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo getGallery(@RequestBody int no) {
		GalleryVo gVo = galleryService.getGallery(no);
		return gVo;
	}
	
	@ResponseBody
	@RequestMapping(value="/gallery/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public int delete(@RequestBody int no) {
		int count = galleryService.delete(no);
		return count;
	}
	
	
	
	
	
	
	
	
}
