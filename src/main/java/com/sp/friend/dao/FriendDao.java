package com.sp.friend.dao;

import java.util.List;

public interface FriendDao {

	void connect(int email1Id, int email2Id);

	List<String> retrieveFriends(String email);

	void subscribe(int requestorId, int targetId);

	void block(int requestorId, int targetId);

	List<String> retrieveActiveFriends(String email);
	
	int getRelationship(int requestorId,int targetId);
	
	boolean checkIsFriend(int emailId,int email2Id);
}
