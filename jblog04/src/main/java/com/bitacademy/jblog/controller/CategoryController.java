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
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.security.Auth;

@Controller
@RequestMapping("/{id:(?!assets).*}/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BlogService blogService;
	
	@Auth(value=true)
	@RequestMapping(value="", method=RequestMethod.GET)
	public String category(@PathVariable String id, Model model) {
		
		BlogVo vo = blogService.findBasic(id);
		
		model.addAttribute("id", id);
		List<CategoryVo> list = categoryService.findCategoryList(id);
		System.out.println(list);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", list.size());
		model.addAttribute("basic",vo );
		return "blog/blog-admin-category";
	}
	
	@Auth(value=true)
	@RequestMapping(value="", method=RequestMethod.POST)
	public String category(@PathVariable String id,CategoryVo categoryVo) {
		
		categoryService.categoryInsert(id,categoryVo);
		
		return "redirect:/"+id+"/category";
	}
	
	@Auth(value=true)
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String delete(@PathVariable String id,@PathVariable Long no) {
		
		categoryService.categoryDelete(no);
		
		return "redirect:/"+id+"/category";
	}
}
