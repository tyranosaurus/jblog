package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.CategoryDao;
import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.UserVo;

@Service
public class CategoryService 
{
	@Autowired
	private CategoryDao categoryDao;
	
	public void defaultCategory(UserVo vo)
	{
		categoryDao.defaultCategory(vo);
	}
	
	public Long insert(Long blogNo, String cateName, String cateDesc)
	{
		Long no = categoryDao.insert(blogNo, cateName, cateDesc);
		return no;
	}
	
	public Long getBlogNo(String userId)
	{
		Long blogNo = categoryDao.getBlogNo(userId);
		
		return blogNo;
	}
	
	public List<CategoryVo> getList(Long blogNo)
	{
		List<CategoryVo> list = categoryDao.getList(blogNo);
		return list;
	}
	
	public void delete(Long no)
	{
		categoryDao.delete(no);
	}
}
