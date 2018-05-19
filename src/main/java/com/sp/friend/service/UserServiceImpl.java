package com.sp.friend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.friend.dao.UserDao;
import com.sp.friend.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public User retrieve(String email) {
		return userDao.retrieve(email);
	}

	@Override
	public int save(String email) {
		User user = userDao.retrieve(email);
		int id;
		if(user == null) {
			id = userDao.save(email);
		}else {
			id = user.getId();
		}
		
		return id;
	}
}
