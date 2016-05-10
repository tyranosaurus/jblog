package com.estsoft.jblog.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVo;
import com.estsoft.jblog.vo.UserVo;

@Repository
public class BlogDao 
{
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo)
	{
		sqlSession.insert("blog.insert", vo);
	}
	
	public BlogVo getBlog(String userId)
	{
		BlogVo blogVo = sqlSession.selectOne("blog.getblog", userId);
		
		return blogVo;
	}
	
	public void basicModify(String userId, String title, String imageUrl)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("title", title);
		map.put("imageUrl", imageUrl);
		
		sqlSession.update("blog.basicmodify", map);
	}
	
	public void imageModify(String userId, String imageUrl) {
	       Map<String, Object> map = new HashMap<String, Object>();
	         map.put("userId", userId);
	         map.put("imageUrl", imageUrl);
	         sqlSession.update("blog.imagemodify", map);
	         
	    }
}
