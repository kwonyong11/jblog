package com.bitacademy.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.CategoryRepository;
import com.bitacademy.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository; 
	
	public void categoryInsert(String id, CategoryVo categoryVo) {
		categoryRepository.categoryInsert(id,categoryVo);
	}

	public void findList(String id) {
		categoryRepository.findList(id);
		
	}
}
