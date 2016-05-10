package com.estsoft.jblog.controller;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.estsoft.jblog.service.PostService;
import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.PostVo;
import com.estsoft.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog")
public class BlogController 
{	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;

	@RequestMapping("/{userId}")
	public String blog(
			@PathVariable("userId") String userId,
			@RequestParam(value="cateNo", required=true, defaultValue="-1") Long categoryNo,
			@RequestParam(value="postNo", required=true, defaultValue="-1") Long postNo,
			Model model)
	{	
			BlogVo blogVo = blogService.getBlog(userId);
			model.addAttribute("blogVo", blogVo);
			
			Long blogNo = categoryService.getBlogNo(userId);
			
			List<CategoryVo> cateList = categoryService.getList(blogNo);
			model.addAttribute("cateList", cateList);
			
			List<PostVo> postList = postService.getList(categoryNo);
			model.addAttribute("postList", postList);
		
			PostVo postVo = postService.getPost(postNo);
			model.addAttribute("postVo", postVo);
			
			if (postNo == -1)
			{
				postVo = postService.getRecentPost(categoryNo);
				model.addAttribute("postVo", postVo);
			}
			
			if (categoryNo == -1 && postNo == -1)
			{
				postVo = postService.getDefaultPost();
				model.addAttribute("postVo", postVo);
				
				postList = postService.getList(postVo.getCategoryNo());
				model.addAttribute("postList", postList);
			}
			
			model.addAttribute("categoryNo", categoryNo);

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
		
		Long blogNo = categoryService.getBlogNo(userId);
		List<CategoryVo> list = categoryService.getList(blogNo);
		
		model.addAttribute("list", list);
		
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
		
	    blogService.basicModify(userId, title, "/jblog/"+imageUrl);
	    
		return "redirect:/blog/" + userId;
	}	

	@RequestMapping("/{userId}/imageUpdate")
	   @ResponseBody
	   public Map<String, Object> ajaxList(@PathVariable( "userId" ) String userId,
	         MultipartHttpServletRequest request, Model model){   
	      
		BlogVo blogVo = blogService.getBlog(userId);
	      model.addAttribute(blogVo);
	      
	      
	      Iterator<String> itr = request.getFileNames(); /* 폼에 파일 선택이 여러개 있으면 여러개 나옴 */
	      Map<String, Object> map = new HashMap<String, Object>();
	      if(itr.hasNext()){ /* 지금은 하나라 if, 여러개면 while */
	         //fileUpload
	         MultipartFile logoFile = request.getFile(itr.next());
	         
	         String imageUrl = blogService.upload(logoFile);
	            

	            map.put("result", "success"); //response로 '결과 : 성공'을 보내줌
	            map.put("data",imageUrl); //response로 '데이터 : 파일URL'을 보내줌
	                 
	            //upload database
	            
	            blogService.imageModify(userId, imageUrl);
	            
	         //return map
	         return map;
	      }else{   
	         return map;
	      }
	   }
}
