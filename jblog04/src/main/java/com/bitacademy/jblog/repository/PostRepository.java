package com.bitacademy.jblog.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void postInsert(PostVo postVo) {
		sqlSession.insert("post.postInsert",postVo);
		
	}

	public List<PostVo> postFind(Long category) {
		
		return sqlSession.selectList("post.postFind", category);
	}

	public PostVo choicePostFind(Long postNo) {
		return sqlSession.selectOne("post.choicePostFind", postNo);
	}
	
}
