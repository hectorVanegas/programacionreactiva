package com.example.accessingmongodbdatarest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessingmongodbdatarest.dao.PersonRepository;
import com.example.accessingmongodbdatarest.document.Person;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	PersonRepository dao;	
	
	@Override
	public Person updateCellNumber(Person person) {
		Optional<Person> objPerson = dao.findById(person.getId());
		Person personBd = new Person();
		
		if(objPerson.isPresent()) {
			
			personBd = objPerson.get();
			personBd.setCellNumber(person.getCellNumber());
			dao.delete(personBd);
			dao.save(personBd);
			
		}
		return personBd ;
	}
	
	@Override
	public Person save(Person person) {
		
			return dao.save(person);
		
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findAllConNombreUpperCase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findAllConNombreUpperCaseRepeat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public boolean delete(Person person) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	@Override
	public Person updateAddress(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person updateEmailAddress(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
