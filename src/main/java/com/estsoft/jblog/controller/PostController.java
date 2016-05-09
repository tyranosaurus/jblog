package com.estsoft.jblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;

@Controller
@RequestMapping("/post")
public class PostController 
{
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/{userId}/insert")
	public String insert(
			@PathVariable("userId") String userId,
			@Valid @ModelAttribute PostVo postVo,
			BindingResult result,
			Model model)
	{
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		
		if (result.hasErrors())
		{
			Long blogNo = categoryService.getBlogNo(userId);
			List<CategoryVo> list = categoryService.getList(blogNo);
			
			model.addAttribute("list", list);
			model.addAllAttributes(result.getModel());  // map 으로 데이터가 들어간다. 
			return "/blog/blog-admin-write";
		}
		
		
		return "redirect:/blog/" + userId;
	}
}
