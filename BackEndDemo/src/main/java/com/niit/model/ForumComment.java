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
@Table(name="ForumComment")
public class ForumComment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int fcommentId;
	@ManyToOne
	@JoinColumn(name="fid")
	private Blog forumPost;
	@ManyToOne
	@JoinColumn(name="username")
	private User fcommentedBy;
	private Date fcommentDate;
	private String fcommentText;
	public int getFcommentId() {
		return fcommentId;
	}
	public void setFcommentId(int fcommentId) {
		this.fcommentId = fcommentId;
	}
	public Blog getForumPost() {
		return forumPost;
	}
	public void setForumPost(Blog forumPost) {
		this.forumPost = forumPost;
	}
	public User getFcommentedBy() {
		return fcommentedBy;
	}
	public void setFcommentedBy(User fcommentedBy) {
		this.fcommentedBy = fcommentedBy;
	}
	public Date getFcommentDate() {
		return fcommentDate;
	}
	public void setFcommentDate(Date fcommentDate) {
		this.fcommentDate = fcommentDate;
	}
	public String getFcommentText() {
		return fcommentText;
	}
	public void setFcommentText(String fcommentText) {
		this.fcommentText = fcommentText;
	}

}
