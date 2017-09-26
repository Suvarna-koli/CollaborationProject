package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;
import com.niit.model.Forum;
import com.niit.model.ForumComment;

public interface ForumDAO {
	public void saveForum(Forum forumPost);

	public List getForums(int fapproved);

	public Forum getForumById(int fid);

	public void updateForumPost(Forum forumPost);

	void addForumComment(ForumComment fComment);

	public List getForumComments(int fid);

}
