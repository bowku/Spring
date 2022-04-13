package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Dogs;

@Repository
public interface DogsRepo extends JpaRepository<Dogs, Integer> {

	List<Dogs> findByNameIgnoreCase(String name);

	List<Dogs> findByAge(Integer age);

}
