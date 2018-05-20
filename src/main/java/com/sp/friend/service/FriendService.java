package com.sp.friend.service;

import java.util.List;

import com.sp.friend.model.SPException;

public interface FriendService {
	
	void connect(String email1, String email2) throws SPException;
	
	List<String> retrieveFriends(String email);
	
	List<String> retrieveCommonFriends(String email1, String email2);
	
	void subscribe(String requestor, String target) throws SPException;
	
	void block(String requestor, String target);
	
	List<String> retrieveActiveFriends(String email);

}
