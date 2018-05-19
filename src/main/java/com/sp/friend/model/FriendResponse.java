package com.sp.friend.model;

import java.util.List;

public class FriendResponse extends SPResponse {
	
	public FriendResponse(List<String> emails) {
		super();
		this.friends = emails;
		this.count = emails.size();
	}

	private List<String> friends;
	private int count;
	
	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	

}
