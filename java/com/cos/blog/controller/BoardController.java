package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping({ "", "/" })
	public String index(Model model,
			@PageableDefault(size = 21, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		model.addAttribute("boards", boardService.글목록(pageable));
		model.addAttribute("boards2", boardService.글목록2());
		return "index";
		
	}
	

	
	// /WEB-INF/views/index.jsp

	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";

	}

	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/updateForm";
	}

// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

	@GetMapping("/board/APIForm")
	public String APIForm() {
		return "board/APIForm";
	}
	
	@GetMapping("/board/ProductsaveForm")
	public String ProductsaveForm() {
		return "board/ProductsaveForm";
	}
	
	@GetMapping("/board/productdetail")
	public String productdetail() {
		return "board/productdetail";
	}
	
	
}
