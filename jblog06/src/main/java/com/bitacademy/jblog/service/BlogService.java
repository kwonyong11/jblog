package com.bitacademy.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.jblog.repository.BlogRepository;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.UserVo;

@Service
public class BlogService {
	
	private static final String SAVE_PATH = "/java-fullstack/jblog";
	
	@Autowired
	private BlogRepository blogRepository;
	
	public List<BlogVo> findKeyword(UserVo vo, String keyword, String which) {
		return blogRepository.findKeyword(keyword,which);
	}

	public BlogVo findBasic(String id) {
		
		return blogRepository.findBasic(id);
		
	}

	public void basicInsert(String id) {
		
		blogRepository.basicInsert(id);
	}

	public void basicUpdate(String id, BlogVo vo) {
		blogRepository.basicUpdate(id,vo);
		
	}

	public String FileUpload(MultipartFile file, String id) {
		byte[] fileData;
		String saveFilename="";
		try {
			String originFilename = file.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1);
			saveFilename = id+"."+extName;
			fileData = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(fileData);
			os.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saveFilename;
	}

	public void basicTitleUpdate(String id, BlogVo vo) {
		blogRepository.basicTitleUpdate(id,vo);
		
	}
}
