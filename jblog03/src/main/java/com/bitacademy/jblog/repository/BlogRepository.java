package com.bitacademy.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	
	@Autowired SqlSession sqlSession;
	
	public List<BlogVo> findKeyword(String keyword,String which){
		Map<String, Object> map=new HashMap<>();
		map.put("k", keyword);
		map.put("w", which);
		return sqlSession.selectList("blog.findKeyword",map);
	}

	public BlogVo findBasic(String id) {
		return sqlSession.selectOne("blog.findBasic", id);
	}

	public void basicInsert(String id) {
		Map<String, Object> map=new HashMap<>();
		map.put("id", id);
		map.put("logo", id+"/spring-logo.jpg");
		sqlSession.insert("blog.basicInsert",id);
		
	}

	public void basicUpdate(String id, BlogVo vo) {
		Map<String, Object> map=new HashMap<>();
		map.put("id", id);
		map.put("title",vo.getTitle());
		map.put("logo",vo.getLogo());
		sqlSession.update("blog.basicUpdate", map);
		
	}
}
