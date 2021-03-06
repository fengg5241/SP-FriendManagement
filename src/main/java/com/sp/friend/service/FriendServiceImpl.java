package com.sp.friend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sp.friend.dao.FriendDao;
import com.sp.friend.model.RelationshipType;
import com.sp.friend.model.SPError;
import com.sp.friend.model.SPException;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	UserService userService;
	
	@Autowired
	FriendDao friendDao;
	
	@Override
	public void connect(String email1, String email2) throws SPException {
		int email1Id = userService.save(email1);
		int email2Id = userService.save(email2);
		
		// Always create connection based on the rule smaller id on the left 
		if(email1Id > email2Id) {
			int tempId = email1Id;
			email1Id = email2Id;
			email2Id = tempId;
		}
		
		boolean isFriend = friendDao.checkIsFriend(email1Id, email2Id);
		if(isFriend) {
			throw new SPException(HttpStatus.FORBIDDEN, SPError.FRIEND_EXISTED);
		}
		int relationship1 = friendDao.getRelationship(email1Id, email2Id);
		if(relationship1 == RelationshipType.BLOCK.getValue()) {
			throw new SPException(HttpStatus.FORBIDDEN, SPError.BLOCK_RELATIONSHIP);
		}
		int relationship2 = friendDao.getRelationship(email2Id, email1Id);
		if(relationship2 == RelationshipType.BLOCK.getValue()) {
			throw new SPException(HttpStatus.FORBIDDEN, SPError.BLOCK_RELATIONSHIP);
		}
		friendDao.connect(email1Id, email2Id);
		
		
	}

	@Override
	public List<String> retrieveFriends(String email) {
		return friendDao.retrieveFriends(email);
	}

	@Override
	public List<String> retrieveCommonFriends(String email1, String email2) {
		List<String> email1Friends = friendDao.retrieveFriends(email1);
		List<String> email2Friends = friendDao.retrieveFriends(email2);
		
		List<String> commonFriends = new ArrayList<String>();
		for (String friend : email1Friends) {
			if(email2Friends.contains(friend)) {
				commonFriends.add(friend);
			}
		}
		return commonFriends;
	}

	@Override
	public void subscribe(String requestor, String target) throws SPException {
		int requestorId = userService.save(requestor);
		int targetId = userService.save(target);
		friendDao.subscribe(requestorId, targetId);

	}

	@Override
	public void block(String requestor, String target) {
		int requestorId = userService.save(requestor);
		int targetId = userService.save(target);
		friendDao.block(requestorId, targetId);
	}

	@Override
	public List<String> retrieveActiveFriends(String email) {
		return friendDao.retrieveActiveFriends(email);
	}

}
