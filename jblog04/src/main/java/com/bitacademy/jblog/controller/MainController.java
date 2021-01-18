package com.bitacademy.jblog.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.jblog.service.BlogService;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.UserVo;
import com.bitacademy.security.AuthUser;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(@AuthUser UserVo vo, Model model) {
		if(vo!=null) {
			model.addAttribute("id", vo.getId());
		}
		
		return "main/index";
	}
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public String index(@AuthUser UserVo vo, Model model, String keyword, String which) {
		if(vo!=null) {
			model.addAttribute("id", vo.getId());
		}
		if(keyword!=null && which!=null) {
			List<BlogVo> list = blogService.findKeyword(vo,keyword,which);
			model.addAttribute("list",list);
		}
		
		model.addAttribute("keyword", keyword);
		return "main/index";
	}
}