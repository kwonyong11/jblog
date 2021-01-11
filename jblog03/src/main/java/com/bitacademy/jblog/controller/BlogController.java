package com.bitacademy.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.jblog.service.BlogService;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.UserVo;
import com.bitacademy.security.AuthUser;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"", "/{category}", "/{category}/{post}" } )
	public String index(
		@PathVariable String id,
		@PathVariable Optional<Long> category,
		@PathVariable Optional<Long> post,
		Model model,
		@AuthUser UserVo vo) {
		
		model.addAttribute("id",vo.getId());
		
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/basic", method=RequestMethod.GET)
	public String basic(
		@PathVariable String id,
		@PathVariable Optional<Long> category,
		@PathVariable Optional<Long> post,
		Model model) {
		
		BlogVo vo = blogService.findBasic(id);
		
		model.addAttribute("id", id);
		model.addAttribute("vo", vo);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/basic", method=RequestMethod.POST)
	public String basic(
		@PathVariable String id,
		BlogVo vo,
		@RequestParam(value="logoFile") MultipartFile File) {
		
		vo.setLogo(blogService.FileUpload(File,id));
		blogService.basicUpdate(id,vo);
		return "redirect:/"+id+"/basic";
	}
	
}