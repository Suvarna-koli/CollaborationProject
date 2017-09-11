package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDAO {

	public List<User> getListOfSuggUser(String username);
	/*public void addFriendRequest(String username,String toId);
	public  List<Friend> getPendingRequest(String username);
	public void updatePendingRequest(Friend pendingRequest);*/
}
