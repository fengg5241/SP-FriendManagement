package com.sp.friend.dao;

import com.sp.friend.model.User;

public interface UserDao {

	User retrieve(String email);

	int save(String email);

}
