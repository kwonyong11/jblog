package com.bitacademy.jblog.repository;

import java.util.HashMap;
import java.util.List;
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
		map.put("description", categoryVo.getDescription());
		
		sqlSession.insert("category.insert", map);
		
	}

	public List<CategoryVo> findCategoryList(String id) {
		return sqlSession.selectList("category.findCategoryList", id);
		
	}

	public void categoryDelete(Long no) {
		sqlSession.delete("category.postDelete", no);
		sqlSession.delete("category.categoryDelete", no);
		
	}
	
	
}
