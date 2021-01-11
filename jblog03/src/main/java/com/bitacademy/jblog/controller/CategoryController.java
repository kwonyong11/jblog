package com.bitacademy.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.jblog.service.CategoryService;
import com.bitacademy.jblog.vo.CategoryVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String category(@PathVariable String id, Model model) {
		
		model.addAttribute("id", id);
		categoryService.findList(id);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String category(@PathVariable String id,CategoryVo categoryVo) {
		
		categoryService.categoryInsert(id,categoryVo);
		
		return "redirect:/"+id+"/category";
	}
}
