package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.PersonJob;;

public interface PersonJobDAO {

	public void addPersonJob(PersonJob p);
	public void updatePersonJob(PersonJob p);
	public List<PersonJob> listPersonJobs();
	public List<PersonJob> listPersonJobs(int personId);
	public PersonJob getPersonJobById(int id);
	public void removePersonJob(int id);
	
}
