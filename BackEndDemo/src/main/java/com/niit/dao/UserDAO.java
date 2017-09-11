package com.niit.dao;

import com.niit.model.User;

public interface UserDAO {
	public void registerUser(User user);
	public User validateUsername(String username);
	public User validateEmail(String email);
	public User login(User user);
	public void updateUser(User user);
	public User getUserByUsername(String username);
	

}
