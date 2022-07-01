package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;


@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	
	
	//갤러리 리스트
	@RequestMapping(value="/gallery/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model){
		System.out.println("GalleryController>list()");
		
		List<GalleryVo> galleryList = galleryService.galleryList();
		model.addAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}
	
	
	//이미지 올리기
	@RequestMapping(value="/gallery/addImg", method= {RequestMethod.GET, RequestMethod.POST})
	public String addImg(@RequestParam("file") MultipartFile file, GalleryVo galleryVo, Model model){
		System.out.println("GalleryController>addImg()");
		System.out.println(file.getOriginalFilename());
		System.out.println(galleryVo);
		
		int saveName = galleryService.saveImg(file, galleryVo);
		model.addAttribute("saveName", saveName);
		
		return "redirect:/gallery/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
