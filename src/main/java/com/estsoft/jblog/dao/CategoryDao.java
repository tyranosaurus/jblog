package com.estsoft.jblog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.CategoryVo;
import com.estsoft.jblog.vo.UserVo;

@Repository
public class CategoryDao 
{
	@Autowired
	private SqlSession sqlSession;
	
	public void defaultCategory(UserVo vo)
	{
		sqlSession.insert("category.defaultcategory", vo);
	}
	
	public Long insert(Long blogNo, String cateName, String cateDesc)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogNo", blogNo);
		map.put("cateName", cateName);
		map.put("cateDesc", cateDesc);
		
		sqlSession.insert("category.insert", map);
		
		Long no = (Long) map.get("no");
		return no;
	}
	
	public Long getBlogNo(String userId)
	{
		Long blogNo = sqlSession.selectOne("category.getblogno", userId);
		return blogNo;
	}
	
	public List<CategoryVo> getList(Long blogNo)
	{
		List<CategoryVo> list = sqlSession.selectList("category.getlist", blogNo);
		return list;
	}
	
	public void delete(Long no)
	{
		sqlSession.delete("category.delete", no);
	}
}
