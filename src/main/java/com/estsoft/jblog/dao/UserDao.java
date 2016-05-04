package com.estsoft.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.UserVo;

@Repository
public class UserDao 
{
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo)
	{
		sqlSession.insert("user.insert", vo);
	}
	
	public UserVo get(UserVo vo)
	{
		UserVo authVo = sqlSession.selectOne("user.selectAuth", vo);
		return authVo;
		
	}
	
	public UserVo get(String id)
	{
		UserVo vo = sqlSession.selectOne("user.selectById", id);
		return vo;
	}
}
