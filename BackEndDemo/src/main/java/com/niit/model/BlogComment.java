package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="blogComment")
public class BlogComment {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int commentId;
		@ManyToOne
		@JoinColumn(name="blogid")
		private Blog blogPost;
		public int getCommentId() {
			return commentId;
		}
		public void setCommentId(int commentId) {
			this.commentId = commentId;
		}
		public Blog getBlogPost() {
			return blogPost;
		}
		public void setBlogPost(Blog blogPost) {
			this.blogPost = blogPost;
		}
		public User getCommentedBy() {
			return commentedBy;
		}
		public void setCommentedBy(User commentedBy) {
			this.commentedBy = commentedBy;
		}
		public Date getCommentDate() {
			return commentDate;
		}
		public void setCommentDate(Date commentDate) {
			this.commentDate = commentDate;
		}
		public String getCommentText() {
			return commentText;
		}
		public void setCommentText(String commentText) {
			this.commentText = commentText;
		}
		@ManyToOne
		@JoinColumn(name="username")
		private User commentedBy;
		private Date commentDate;
		private String commentText;
		

}
