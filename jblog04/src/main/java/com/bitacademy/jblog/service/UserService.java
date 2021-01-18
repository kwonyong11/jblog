package com.bitacademy.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.UserRepository;
import com.bitacademy.jblog.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public boolean join(UserVo vo) {
		int count = userRepository.insert(vo);
		
		return count == 1;
	}
	
	public UserVo getUser(UserVo vo) {
		return userRepository.findByIdAndPassword(vo);
	}
}
