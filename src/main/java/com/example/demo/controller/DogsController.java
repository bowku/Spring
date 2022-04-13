package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Dogs;
import com.example.demo.service.DogsService;

@RestController
@CrossOrigin
public class DogsController {

	private DogsService service;

	@Autowired
	public DogsController(DogsService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create") // 201 - created
	public ResponseEntity<Dogs> createDog(@RequestBody Dogs p) {
		Dogs created = this.service.create(p);
		ResponseEntity<Dogs> response = new ResponseEntity<Dogs>(created, HttpStatus.CREATED);
		return response;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Dogs>> getallPeeps() {
		return ResponseEntity.ok(this.service.getAll());
	}

	@GetMapping("/get/{id}")
	public Dogs getPerson(@PathVariable Integer id) {
		return this.service.getOne(id);
	}

	@PutMapping("/replace/{id}")
	public ResponseEntity<Dogs> replaceDogs(@PathVariable Integer id, @RequestBody Dogs newDogs) {
		Dogs body = this.service.update(id, newDogs);
		ResponseEntity<Dogs> response = new ResponseEntity<Dogs>(body, HttpStatus.ACCEPTED);
		return response;
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeDogs(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Dogs>> getDogsByName(@PathVariable String name) {
		List<Dogs> found = this.service.getPeepsByName(name);
		return ResponseEntity.ok(found);
	}

	@GetMapping("/getByAge/{age}")
	public ResponseEntity<List<Dogs>> getDogsByAge(@PathVariable Integer age) {
		List<Dogs> found = this.service.getPeepsByAge(age);
		return ResponseEntity.ok(found);
	}

}
