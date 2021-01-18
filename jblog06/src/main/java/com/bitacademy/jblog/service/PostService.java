package com.bitacademy.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.PostRepository;
import com.bitacademy.jblog.vo.PostVo;


@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public void postInsert(PostVo postVo) {
		postRepository.postInsert(postVo);
		
	}

	public List<PostVo> postFind(Long category) {
		
		return postRepository.postFind(category);
	}

	public PostVo choicePostFind(Long postNo) {
		
		return postRepository.choicePostFind(postNo);
	} 
	
}
