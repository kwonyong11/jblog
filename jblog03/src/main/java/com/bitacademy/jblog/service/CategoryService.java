package com.bitacademy.jblog.service;

import java.util.List;

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

	public List<CategoryVo> findCategoryList(String id) {

		return categoryRepository.findCategoryList(id);
		
	}

	public void categoryDelete(Long no) {
		categoryRepository.categoryDelete(no);
		
	}
}
