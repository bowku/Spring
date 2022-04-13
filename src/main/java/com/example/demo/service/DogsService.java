package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Dogs;
import com.example.demo.repo.DogsRepo;

@Service
public class DogsService implements serviceIF<Dogs> {

	private DogsRepo repo;

	@Autowired
	public DogsService(DogsRepo repo) {
		super();
		this.repo = repo;
	}

	public Dogs create(Dogs p) {
		Dogs created = this.repo.save(p);
		return created;
	}

	public List<Dogs> getAll() {
		return this.repo.findAll();
	}

	public Dogs getOne(Integer id) {
		Optional<Dogs> found = this.repo.findById(id);
		return found.get();
	}

	public Dogs update(Integer id, Dogs newDogs) {
		Dogs existing = this.repo.findById(id).get();
		existing.setAge(newDogs.getAge());
		existing.setName(newDogs.getName());
		existing.setBreed(newDogs.getBreed());
		Dogs updated = this.repo.save(existing);
		return updated;
	}

	public void remove(@PathVariable Integer id) {
		this.repo.deleteById(id);
	}

	public List<Dogs> getPeepsByName(String name) {
		List<Dogs> found = this.repo.findByNameIgnoreCase(name);
		return found;
	}

	public List<Dogs> getPeepsByAge(Integer age) {
		List<Dogs> found = this.repo.findByAge(age);
		return found;
	}

}
