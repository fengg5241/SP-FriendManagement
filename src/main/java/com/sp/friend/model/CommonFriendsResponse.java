package com.sp.friend.model;

import java.util.List;

public class CommonFriendsResponse {
	public CommonFriendsResponse(List<String> friends) {
		super();
		this.friends = friends;
	}

	private List<String> friends;

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	
}
