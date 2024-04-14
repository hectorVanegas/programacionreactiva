package com.example.accessingmongodbdatarest.controller;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingmongodbdatarest.document.Person;
import com.example.accessingmongodbdatarest.service.PersonService;

@RestController
@RequestMapping ("/api/person")
public class PersonControler {

	@Autowired
	PersonService service;
	
	@PostMapping
	public ResponseEntity<Person> crear(@RequestBody Person person){
		
		if(person.getCreateAt()==null) {
			person.setCreateAt(new Date());
		}
		Person personBd = service.save(person);
		
		
		return ResponseEntity.ok(personBd);
		
	}

	@PutMapping
	public ResponseEntity<Person> updateCellNUmber(@RequestBody Person person){
			
		return ResponseEntity.ok(service.updateCellNumber(person));
		
	}
}
