package com.journaldev.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.dao.PersonJobDAO;
import com.journaldev.spring.model.PersonJob;

@Service
public class PersonJobServiceImpl implements PersonJobService{
	
	private PersonJobDAO personJobDAO;
	
	public void setPersonJobDAO(PersonJobDAO personJobDAO) {
		this.personJobDAO = personJobDAO;
	}

	@Override
	@Transactional
	public void addPersonJob (PersonJob p) {
		this.personJobDAO.addPersonJob(p);
	}

	@Override
	@Transactional
	public void updatePersonJob(PersonJob p) {
		this.personJobDAO.updatePersonJob(p);
	}

	@Override
	@Transactional
	public List<PersonJob> listPersonJobs() {
		return this.personJobDAO.listPersonJobs();
	}
	
	@Override
	@Transactional
	public List<PersonJob> listPersonJobs(int personId) {
		return this.personJobDAO.listPersonJobs(personId);
	}

	@Override
	@Transactional
	public PersonJob getPersonJobById(int id) {
		return this.personJobDAO.getPersonJobById(id);
	}

	@Override
	@Transactional
	public void removePersonJob(int id) {
		this.personJobDAO.removePersonJob(id);
	}
}
