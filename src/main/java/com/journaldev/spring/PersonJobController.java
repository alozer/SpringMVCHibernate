package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.PersonJob;
import com.journaldev.spring.service.PersonJobService;

@Controller
public class PersonJobController {
	
	private PersonJobService personJobService;
	
	@Autowired(required=true)
	@Qualifier(value="personJobService")
	public void setPersonJobService(PersonJobService ps){
		this.personJobService = ps;
	}
	
	@RequestMapping(value = "/personJob/list/{personId}", method = RequestMethod.GET)
	public String listPersons(@PathVariable("personId") int personId, Model model) {
		System.out.println("listPersons started");
		PersonJob personJob = new PersonJob();
		personJob.setPersonId(personId);
		model.addAttribute("personJob", personJob);
		model.addAttribute("listPersonJobs", this.personJobService.listPersonJobs(personId));
		System.out.println("listPersons finished");
		return "personJob";
	}
	
	//For add and update person both
	@RequestMapping(value= "/personJob/add/{personId}", method = RequestMethod.POST)
	public String addPersonJob(@PathVariable("personId") int personId, @ModelAttribute("personJob") PersonJob p, Model model){
		
		if(p.getId() == 0){
			//new person, add it
			System.out.println("addPersonJob started ");
			System.out.println("personId: "+p.getPersonId());
			System.out.println("personId: "+personId);
			System.out.println("title: "+p.getTitle());
			System.out.println("definition: "+p.getDefinition());
			p.setPersonId(personId);
			this.personJobService.addPersonJob(p);
			System.out.println("addPersonJob finished ");
		}else{
			//existing person, call update
			System.out.println("updatePersonJob started ");
			System.out.println("personId: "+p.getPersonId());
			this.personJobService.updatePersonJob(p);
			System.out.println("updatePersonJob finished ");
		}
		System.out.println("personJob/list before ");
		model.addAttribute("personId", personId);
		return "redirect:/personJob/list/{personId}";
		
	}
	
	@RequestMapping("/personJob/remove/{personId}/{id}")
    public String removePerson(@PathVariable("personId") int personId, @PathVariable("id") int id, Model model){
		
        this.personJobService.removePersonJob(id);
        model.addAttribute("personId", personId);
        return "redirect:/personJob/list/{personId}";
    }
 
    @RequestMapping("personJob/edit/{personId}/{id}")
    public String editPersonJob(@PathVariable("personId") int personId,  @PathVariable("id") int id,  Model model){
        model.addAttribute("personJob", this.personJobService.getPersonJobById(id));
        model.addAttribute("listPersonJobs", this.personJobService.listPersonJobs(personId));
        return "personJob";
    }
	
}
