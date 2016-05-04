package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/joinform")
	public String joinform()
	{
		return "/user/join";
	}
	
	@RequestMapping("/checkemail")
	@ResponseBody
	public Map<String, Object> checkEmail(@RequestParam(value="id", required=true, defaultValue="") String id)
	{      
		//Map<> 대신 Object 그냥 객체 반환이라고 써도됨
		UserVo vo = userService.getUser(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", vo == null);
		
		return map;
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@Valid @ModelAttribute UserVo vo, BindingResult result, Model model)
	{
		if (result.hasErrors())
		{
			model.addAllAttributes(result.getModel());  // map 으로 데이터가 들어간다. 
			return "/user/join";
		}
		
		userService.join(vo);
		blogService.insert(vo);
		return "/user/joinsuccess";  // 리다이렉팅
	}
	
	@RequestMapping("/loginform")
	public String loginform()
	{
		return "/user/login";
	}
}
