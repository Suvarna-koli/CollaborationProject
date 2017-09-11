package com.niit.dao;

import com.niit.model.ProfileImage;

public interface ProfileImageDAO {
	public void uploadProfilePic(ProfileImage profileImage);

	public ProfileImage getProfilePic(String username);


}
