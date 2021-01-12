package com.bitacademy.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.jblog.service.BlogService;
import com.bitacademy.jblog.service.CategoryService;
import com.bitacademy.jblog.service.PostService;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;


@Controller
@RequestMapping("/{id:(?!assets).*}/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String write(@PathVariable String id, Model model) {
		
		List<CategoryVo> categoryVo = categoryService.findCategoryList(id);
		BlogVo vo = blogService.findBasic(id);
		
		System.out.println(categoryVo);
		model.addAttribute("category", categoryVo);
		model.addAttribute("basic", vo);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String write(@PathVariable String id, Model model, PostVo postVo) {
		postService.postInsert(postVo);
		
		return "redirect:/"+id+"/post";
	}
}
