package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;
import com.niit.model.BlogComment;

public interface BlogPostDAO {

	public void saveBlog(Blog blogPost);

	public List getBlogs(int approved);

	public Blog getBlogById(int blogid);

	public void updateBlogPost(Blog blogPost);

	void addComment(BlogComment blogComment);

	
	public List getBlogComments(int blogid);
}
