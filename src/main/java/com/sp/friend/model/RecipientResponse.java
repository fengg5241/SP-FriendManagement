package com.sp.friend.model;

import java.util.List;

public class RecipientResponse extends SPResponse {

	public RecipientResponse(List<String> recipients) {
		super();
		this.recipients = recipients;
	}

	private List<String> recipients;

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
	
	

}
