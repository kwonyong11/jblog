package com.bitacademy.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void categoryInsert(String id, CategoryVo categoryVo) {
		Map<String, Object> map=new HashMap<>();
		map.put("id", id);
		map.put("name", categoryVo.getName());
		map.put("desc", categoryVo.getDesc());
		
		sqlSession.insert("category.insert", map);
		
	}

	public void findList(String id) {
		
		
	}
	
	
}
