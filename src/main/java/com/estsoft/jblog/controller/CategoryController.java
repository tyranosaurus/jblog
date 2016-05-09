package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.jblog.annotation.AuthUser;
import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.UserVo;

@Controller
@RequestMapping("/category")
public class CategoryController 
{
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/{userId}/insert")
	@ResponseBody
	public Map<String, Object> insert(
			@PathVariable("userId") String userId,
			@AuthUser UserVo authVo,
			@RequestParam("name") String cateName, 
			@RequestParam("desc") String cateDesc,
			Model model)
	{	
		BlogVo blogVo = blogService.getBlog(userId);
		model.addAttribute("blogVo", blogVo);
		
		Long blogNo = categoryService.getBlogNo(authVo.getId());
		Long no = categoryService.insert(blogNo, cateName, cateDesc); // 카테고리 넘버.
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", no);
		
		return map;
	}
	
	@RequestMapping("/{userId}/list2")
	@ResponseBody
	public Map<String, Object> getList2(
			@PathVariable("userId") String userId)
	{
		Long blogNo = categoryService.getBlogNo(userId);
		List<CategoryVo> list = categoryService.getList(blogNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		
		return map;
	}
	
	@RequestMapping("/{userId}/delete")
	@ResponseBody
	public Map<String, Object> delete(
			@PathVariable("userId") String userId,
			@RequestParam("no") long no)
	{
		categoryService.delete(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		
		return map;
	}
}
