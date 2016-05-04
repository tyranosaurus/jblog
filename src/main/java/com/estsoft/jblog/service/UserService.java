package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.UserDao;
import com.estsoft.jblog.vo.UserVo;

@Service
public class UserService 
{
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo vo)
	{
		userDao.insert(vo);
	}
	
	public UserVo login(UserVo vo)
	{
		UserVo authUser = userDao.get(vo);
		
		return authUser;
	}
	
	public UserVo getUser(String id)
	{
		UserVo vo = userDao.get(id);
		return vo;
	}
}
