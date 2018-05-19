package com.sp.friend.service;

import com.sp.friend.model.User;

public interface UserService {
	
	User retrieve(String email);
	
	int save(String email);

}
