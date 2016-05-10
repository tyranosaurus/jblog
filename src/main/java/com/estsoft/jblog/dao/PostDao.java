package com.estsoft.jblog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.PostVo;

@Repository
public class PostDao 
{
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(PostVo postVo)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryNo", postVo.getCategoryNo());
		map.put("title", postVo.getTitle());
		map.put("content", postVo.getContent());
		
		sqlSession.insert("post.insert", map);
	}

	public List<PostVo> getList(Long categoryNo)
	{
		List<PostVo> list = sqlSession.selectList("post.getlist", categoryNo);
		
		return list;
	}
	
	public PostVo getPost(Long postNo)
	{
		PostVo postVo = sqlSession.selectOne("post.getpost", postNo);
		return postVo;
	}
	
	public PostVo getRecentPost(Long categoryNo)
	{
		PostVo postVo = sqlSession.selectOne("post.getrecentpost", categoryNo);
		return postVo;
	}
	
	public PostVo getDefaultPost()
	{
		PostVo postVo = sqlSession.selectOne("post.getdefaultpost");
		return postVo;
	}
	
}