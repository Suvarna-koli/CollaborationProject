package com.niit.dao;

import java.util.List;

import com.niit.model.Blog;

public interface BlogPostDAO {

	public void saveBlog(Blog blogPost);

	public List getblogs(int approved);

	public Blog getBlogById(int blogid);

	public void updateBlogPost(Blog blogPost);

	/*void addComment(BlogComment blogComment);

	List<BlogComment> getBlogComments(int blogId);*/
}
