package com.sp.friend.service;

import java.util.List;

public interface FriendService {
	
	void connect(String email1, String email2);
	
	List<String> retrieveFriends(String email);
	
	List<String> retrieveCommonFriends(String email1, String email2);
	
	void subscribe(String requestor, String target);
	
	void block(String requestor, String target);
	
	List<String> retrieveActiveFriends(String email);

}
