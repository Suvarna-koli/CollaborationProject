package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Forum")
public class Forum {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int fid;
	
	@NotEmpty
	private String ftitle;
	
	@NotEmpty
	private String description;
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFtitle() {
		return ftitle;
	}
	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getFcreatedBy() {
		return fcreatedBy;
	}
	public void setFcreatedBy(User fcreatedBy) {
		this.fcreatedBy = fcreatedBy;
	}
	public Date getFpostedOn() {
		return fpostedOn;
	}
	public void setFpostedOn(Date fpostedOn) {
		this.fpostedOn = fpostedOn;
	}
	public boolean isFapproved() {
		return fapproved;
	}
	public void setFapproved(boolean fapproved) {
		this.fapproved = fapproved;
	}
	@ManyToOne
	@JoinColumn(name="username")
	private User fcreatedBy;
	private Date fpostedOn;
	private boolean fapproved;
	

}
