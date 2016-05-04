package com.estsoft.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.vo.BlogVo;

@Controller
@RequestMapping("/blog")
public class BlogController 
{
	private static final String SAVE_PATH = "/temp";
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/{userId}")
	public String blog(@PathVariable("userId") String userId, Model model)
	{	
		BlogVo blogVo = blogService.getBlog(userId);
		
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/blog-main";
	}
	
	@RequestMapping("/{userId}/blogmanaging")
	public String blogmanaging(@PathVariable("userId") String userId, Model model)
	{
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/blog-admin-basic";
	}
	
	@RequestMapping("/{userId}/category")
	public String category(@PathVariable("userId") String userId, Model model)
	{
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/blog-admin-category";
	}
	
	@RequestMapping("/{userId}/write")
	public String write(@PathVariable("userId") String userId, Model model)
	{
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/blog-admin-write";
	}
	
	@RequestMapping("/{userId}/basicmodify")
	public String basicmodify(
			@PathVariable("userId") String userId, 
			@RequestParam("title") String title, 
			@RequestParam("logo-file") MultipartFile logoFile,
			Model model)
	{
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		if( logoFile.isEmpty() == false ) 
		{					
	        String fileOriginalName = logoFile.getOriginalFilename();
	        String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );  // .확장자
	        String saveFileName = blogService.genSaveFileName(extName);   // 파일 이름 새로 만들어줌 
	        blogService.writeFile( logoFile, SAVE_PATH, saveFileName );

	        String imageUrl = "/jblog/product-images/" + saveFileName;
	        blogService.basicModify(userId, title, imageUrl);
		}
		return "redirect:/blog/" + userId;
	}
}
