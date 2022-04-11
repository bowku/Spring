package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Dogs;

@RestController
public class DogsController {

	private List<Dogs> peeps = new ArrayList<>();

	@PostMapping("/create") //201 - created
	public ResponseEntity<Dogs> createDog(@RequestBody Dogs p) {
		this.peeps.add(p);
		Dogs created = this.peeps.get(this.peeps.size() - 1);
		ResponseEntity<Dogs> response = new ResponseEntity<Dogs>(created,HttpStatus.CREATED); 
		return response;
	}

	@GetMapping("/getAll") 
	public ResponseEntity<List<Dogs>> getallPeeps() {
		return ResponseEntity.ok(this.peeps);
	}

	@GetMapping("/get/{id}")
	public Dogs getPerson(@PathVariable Integer id) {
		return this.peeps.get(id);
	}

	@PutMapping("/replace/{id}")
	public ResponseEntity<Dogs> replaceDogs(@PathVariable Integer id, @RequestBody Dogs newPerson) {
		Dogs body = this.peeps.set(id, newPerson);
		ResponseEntity<Dogs> response = new ResponseEntity<Dogs>(body,HttpStatus.ACCEPTED); 
		return response;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeDogs(@PathVariable Integer id) {
		this.peeps.remove(id.intValue());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
