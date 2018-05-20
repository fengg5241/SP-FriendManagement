package com.sp.friend.dao;

import java.util.List;

import com.sp.friend.model.SPException;

public interface FriendDao {

	void connect(int email1Id, int email2Id) throws SPException;

	List<String> retrieveFriends(String email);

	void subscribe(int requestorId, int targetId) throws SPException;

	void block(int requestorId, int targetId);

	List<String> retrieveActiveFriends(String email);
	
	int getRelationship(int requestorId,int targetId);
	
	boolean checkIsFriend(int emailId,int email2Id);
}
