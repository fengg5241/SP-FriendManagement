package com.sp.friend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sp.friend.model.FriendResponse;
import com.sp.friend.model.RecipientResponse;
import com.sp.friend.model.SPResponse;
import com.sp.friend.service.FriendService;

@RestController
public class FriendController {

	@Autowired
	FriendService friendService;

	@RequestMapping(value="/connect",method = RequestMethod.POST)
	public SPResponse connect(@RequestParam(value = "friends") String[] emails)  {
		friendService.connect(emails[0], emails[1]);
		return new SPResponse();
	}

	@RequestMapping("/retrieveFriends")
	public FriendResponse retrieveFriends(@RequestParam(value = "email") String email){
		List<String> friends = friendService.retrieveFriends(email);
		
		return new FriendResponse(friends);
	}

	@RequestMapping("/retrieveCommonFriends")
	public FriendResponse retrieveCommonFriends(@RequestParam(value = "friends") String[] emails) {
		List<String> commonFriends = friendService.retrieveCommonFriends(emails[0], emails[1]);
		return new FriendResponse(commonFriends);
	}

	@RequestMapping("/subscribe")
	public SPResponse subscribe(@RequestParam(value = "requestor") String requestor,
			@RequestParam(value = "target") String target)  {
		friendService.subscribe(requestor, target);
		return new SPResponse();
	}

	@RequestMapping("/block")
	public SPResponse block(@RequestParam(value = "requestor") String requestor,
			@RequestParam(value = "target") String target) {
		friendService.block(requestor, target);
		return new SPResponse();
	}

	@RequestMapping("/retrieveActiveFriends")
	public RecipientResponse retrieveActiveFriends(@RequestParam(value = "sender") String sender,
			@RequestParam(value = "text") String text)  {
		List<String> activeFriends = friendService.retrieveActiveFriends(sender);
		return new RecipientResponse(activeFriends);
	}
}
