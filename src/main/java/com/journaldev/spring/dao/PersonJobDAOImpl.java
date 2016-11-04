package com.journaldev.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.PersonJob;

@Repository
public class PersonJobDAOImpl implements PersonJobDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addPersonJob(PersonJob p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Person saved successfully, Person Details="+p);
	}

	@Override
	public void updatePersonJob (PersonJob p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonJob> listPersonJobs() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PersonJob> personJobsList = session.createQuery("from PersonJob").list();
		for(PersonJob p : personJobsList){
			logger.info("Person List::"+p);
		}
		return personJobsList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PersonJob> listPersonJobs(int personId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from PersonJob pj where personId = :personId");
		query.setParameter("personId", personId);
		List<PersonJob> personJobsList = query.list();
		query.setParameter("personId", personId);
		for(PersonJob p : personJobsList){
			logger.info("Person List::"+p);
		}
		return personJobsList;
	}

	@Override
	public PersonJob getPersonJobById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		PersonJob p = (PersonJob) session.load(PersonJob.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	@Override
	public void removePersonJob(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		PersonJob p = (PersonJob) session.load(PersonJob.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details="+p);
	}

}
