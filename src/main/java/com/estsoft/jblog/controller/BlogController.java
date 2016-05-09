package com.estsoft.jblog.controller;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.estsoft.jblog.annotation.AuthUser;
import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog")
public class BlogController 
{	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/{userId}")
	public String blog(
			@PathVariable("userId") String userId, 
			Model model)
	{	
			BlogVo blogVo = blogService.getBlog(userId);
			model.addAttribute("blogVo", blogVo);
			model.addAttribute("authId", userId);

		return "/blog/blog-main";
	}
	
	@RequestMapping("/{userId}/blogmanaging")
	public String blogmanaging(
			@AuthUser UserVo authUser,
			@PathVariable("userId") String userId, 
			Model model)
	{
		if (authUser.getId().equals(userId))
		{
			BlogVo blogVo = blogService.getBlog(userId);
			model.addAttribute("blogVo", blogVo);
			
			return "/blog/blog-admin-basic";
		}
		return "redirect:/blog/{userId}";
	}
	
	@RequestMapping("/{userId}/category")
	public String category(
			@PathVariable("userId") String userId, 
			Model model)
	{
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
				
		return "/blog/blog-admin-category";
	}
	
	@RequestMapping("/{userId}/write")
	public String write(
			@PathVariable("userId") String userId, 
			Model model)
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
		
		String imageUrl = blogService.upload(logoFile);
		
	    blogService.basicModify(userId, title, imageUrl);
		
		return "redirect:/blog/" + userId;
	}	
	
	@RequestMapping(value = "/logoupload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logoupload(MultipartHttpServletRequest request)
	{	
		Iterator<String> itr = request.getFileNames();
		String filename = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(itr.hasNext()){
			//fileUpload
			MultipartFile mpf = request.getFile(itr.next());
			filename = blogService.upload(mpf);

			map.put("result", "success");
			map.put("data", filename);	
			return map;
		}
		else
		{	
			return map;
		}
	}
}
