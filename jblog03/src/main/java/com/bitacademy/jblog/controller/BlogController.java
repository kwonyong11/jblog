package com.bitacademy.jblog.controller;

import java.util.List;
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
import com.bitacademy.jblog.service.CategoryService;
import com.bitacademy.jblog.service.PostService;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;
import com.bitacademy.jblog.vo.UserVo;
import com.bitacademy.security.Auth;
import com.bitacademy.security.AuthUser;

@Controller
@RequestMapping("/{id:(?!assets|jimages).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping({"", "/{category}", "/{category}/{post}" } )
	public String index(
		@PathVariable String id,
		@PathVariable Optional<Long> category,
		@PathVariable Optional<Long> post,
		Model model) {
		int checkCategory = 1;
		int checkPost = 1;
		
		Long categoryNo = null;
		List<CategoryVo> categoryList=category(id);
		if(category.isEmpty()) {
			if(categoryList.isEmpty()) {
				checkCategory=0;
			}
			else {
				categoryNo=categoryService.findCategoryList(id).get(0).getNo();
			}
		}
		else {
			categoryNo=category.get();
		}
		
		Long postNo = null;
		List<PostVo> postList = post(categoryNo,id); 
		if(post.isEmpty()) {
			if(postList.isEmpty()) {
				checkPost = 0;
			}
			else {
				postNo = postService.postFind(categoryNo).get(0).getNo();
			}
		}
		else {
			postNo = post.get();
		}
		
		BlogVo blogVo = blogService.findBasic(id);
		PostVo choicePost = choicePostFind(postNo);
		model.addAttribute("id",id);
		model.addAttribute("basic", blogVo);
		model.addAttribute("category", categoryList);
		model.addAttribute("categoryNo", categoryNo);
		model.addAttribute("postList", postList);
		model.addAttribute("post",choicePost);
		model.addAttribute("checkCategory",checkCategory);
		model.addAttribute("checkPost",checkPost);
		return "blog/blog-main";
	}

	@Auth(value=true)
	@RequestMapping(value="/basic", method=RequestMethod.GET)
	public String basic(
		@PathVariable String id,
		Model model) {
		
		BlogVo vo = blogService.findBasic(id);
		model.addAttribute("id", id);
		model.addAttribute("basic", vo);
		return "blog/blog-admin-basic";
	}
	
	@Auth(value=true)
	@RequestMapping(value="/basic", method=RequestMethod.POST)
	public String basic(
		@PathVariable String id,
		BlogVo vo,
		@RequestParam(value="logoFile") MultipartFile File) {
		if(File.isEmpty()) {
			blogService.basicTitleUpdate(id,vo);
		}
		else {
			vo.setLogo(blogService.FileUpload(File,id));
			blogService.basicUpdate(id,vo);
		}
		return "redirect:/"+id+"/basic";
	}
	
	public List<CategoryVo> category(String id) {

		return categoryService.findCategoryList(id);
	}
	
	private List<PostVo> post(Long category,String id) {
		return postService.postFind(category);
	}
	
	private PostVo choicePostFind(Long postNo) {
		return postService.choicePostFind(postNo);
	}
}