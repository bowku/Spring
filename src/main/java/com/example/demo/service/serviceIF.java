package com.example.demo.service;

import java.util.List;

public interface serviceIF<T> {
	
	T create(T t);
	
	List<T> getAll();
	
	T getOne(Integer id);
	
	T update(Integer id, T t);
	
	void remove(Integer id);
	
}
