package com.sp.friend.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sp.friend.model.RelationshipType;
import com.sp.friend.model.SPError;
import com.sp.friend.model.SPException;

@Component
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void connect(int email1Id, int email2Id) throws SPException {
		String sql = "insert into sp_friend(usera_id,userb_id) values(?,?)";
		try {
			jdbcTemplate.update(sql, email1Id, email2Id);
		} catch (DuplicateKeyException e) {
			throw new SPException(HttpStatus.FORBIDDEN, SPError.FRIEND_EXISTED);
		}

	}

	@Override
	public List<String> retrieveFriends(String email) {
		String sql = "select distinct email from sp_user where id in "
				+ "(select f.userb_id FROM sp_friend f WHERE f.usera_id in "
				+ "(select id from sp_user where email = ?))";
		List<String> rightFriends = jdbcTemplate.queryForList(sql, new Object[] {email}, String.class);
		
		String sql1 = "select distinct email from sp_user where id in "
				+ "(select f.usera_id FROM sp_friend f WHERE f.userb_id in "
				+ "(select id from sp_user where email = ?))";
		List<String> leftFriends = jdbcTemplate.queryForList(sql1, new Object[] {email}, String.class);
		for (String friend : leftFriends) {
			if(!rightFriends.contains(friend)) {
				rightFriends.add(friend);
			}
		}
		return rightFriends;
	}

	@Override
	public void subscribe(int requestorId, int targetId) throws SPException {
		String sql = null;
		int relationship = getRelationship(requestorId,targetId);
		if(relationship == RelationshipType.NONE.getValue()) {
			sql = "insert into sp_interaction(usera_id,userb_id,relationship) values(?,?,?)";
			jdbcTemplate.update(sql,requestorId, targetId,RelationshipType.SUBSCRIBE.getValue());
		}else if(relationship == RelationshipType.BLOCK.getValue()){
			throw new SPException(HttpStatus.FORBIDDEN, SPError.BLOCK_RELATIONSHIP);
		}else { // already subscribe, do nothing
		}
	}


	@Override
	public void block(int requestorId, int targetId) {
		String sql = null;
		int relationship = getRelationship(requestorId,targetId);
		if(relationship == RelationshipType.NONE.getValue()) {
			sql = "insert into sp_interaction(usera_id,userb_id,relationship) values(?,?,?)";
			jdbcTemplate.update(sql,requestorId, targetId,RelationshipType.BLOCK.getValue());
		}else if(relationship == RelationshipType.SUBSCRIBE.getValue()){
			sql = "update sp_interaction set relationship = ? where usera_id = ? and userb_id = ?";
			jdbcTemplate.update(sql,RelationshipType.BLOCK.getValue(),requestorId, targetId);
		}else { // already BLOCK, do nothing
		}
	}

	@Override
	public List<String> retrieveActiveFriends(String email) {
		List<String> friends = retrieveFriends(email);
		String sql = "select email from sp_user u, sp_interaction i " + 
				"where i.relationship = 1 and i.userb_id in "
				+ "(select id from sp_user s where s.email = ?) and i.usera_id = u.id";
		List<String> subscribedFriends = jdbcTemplate.queryForList(sql, new Object[] {email}, String.class);
		
		String sql1 = "select email from sp_user u, sp_interaction i " + 
				"where i.relationship = 2 and i.userb_id in "
				+ "(select id from sp_user s where s.email = ?) and i.usera_id = u.id";
		List<String> blockedFriends = jdbcTemplate.queryForList(sql1, new Object[] {email}, String.class);
		
		List<String> activeFriends = new ArrayList<String>();
		for (String friend : friends) {
			if(!blockedFriends.contains(friend)) {
				activeFriends.add(friend);
			}
		}
		
		for (String friend : subscribedFriends) {
			if(!activeFriends.contains(friend)) {
				activeFriends.add(friend);
			}
		}
		return activeFriends;
	}

	@Override
	public int getRelationship(int requestorId, int targetId) {
		String sql = "SELECT relationship FROM sp_interaction WHERE usera_id = ? and userb_id = ?";
		List<Integer> relationships = jdbcTemplate.queryForList(sql, new Object[] {requestorId,targetId},Integer.class);
		int relationship = 0;
		if(relationships.size() > 0) {
			relationship = relationships.get(0);
		}
		return relationship;
	}

	@Override
	public boolean checkIsFriend(int emailId, int email2Id) {
		String sql = "SELECT usera_Id FROM sp_friend WHERE usera_id = ? and userb_id = ?";
		List<Integer> friendIds = jdbcTemplate.queryForList(sql, new Object[] {emailId,email2Id},Integer.class);
		return friendIds.size() > 0;
	}

}
