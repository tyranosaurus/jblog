package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.PostDao;
import com.estsoft.jblog.vo.PostVo;

@Service
public class PostService 
{
	@Autowired
	private PostDao postDao;
	
	public void insert(PostVo postVo)
	{
		postDao.insert(postVo);
	}
	
	public List<PostVo> getList(Long categoryNo)
	{
		List<PostVo> list = postDao.getList(categoryNo);
		
		return list;
	}
	
	public PostVo getPost(Long postNo)
	{
		PostVo postVo = postDao.getPost(postNo);
		return postVo;
	}
	
	public PostVo getRecentPost(Long categoryNo)
	{
		PostVo postVo = postDao.getRecentPost(categoryNo);
		return postVo;
	}
	
	public PostVo getDefaultPost()
	{
		PostVo postVo = postDao.getDefaultPost();
		return postVo;
	}
}
