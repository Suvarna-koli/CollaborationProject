package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;

@Repository
@Transactional
public class JobDAOImpl implements JobDAO {

	

	@Autowired	
	private SessionFactory sessionFactory;

		public void saveJob(Job job) {
			Session session=sessionFactory.openSession();
			session.save(job);
		session.flush();
		session.close();
		}

		
		public List<Job> getAllJobs() {
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Job");
			return query.list();
		}

		public Job getJobById(int id) {
			Session session=sessionFactory.getCurrentSession();
			Job job=(Job)session.get(Job.class,id);
			return job;
		}




	}

