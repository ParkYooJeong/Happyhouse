package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.service.BoardService;

@RestController
@CrossOrigin
public class BoardController {
	@Autowired
	BoardService service;// interface type

	@GetMapping(value = "/board" )
	public List<BoardDto> searchAll() {//검색
		return service.selectAll();
	}
	
	@DeleteMapping(value = "/board/delete/{num}")
	public void delete(@PathVariable String num) {//회원삭제
		service.delete(num);
	}
	
	@GetMapping(value = "/board/search/{word}" )
	public List<BoardDto> search(@PathVariable String word, HttpServletRequest request) {//검색(내용)
		return service.search(word);
	}
	@GetMapping(value = "/board/searchTitle/{word}" )
	public List<BoardDto> searchTitle(@PathVariable String word, HttpServletRequest request) {//검색(제목)
		return service.searchTitle(word);
	}
	
	@PostMapping(value = "/board" )
	public void insert(@RequestBody BoardDto b) {//검색
		service.insert(b);
	}
	
	@PutMapping(value = "/board" )
	public void update(@RequestBody BoardDto b) {//검색
		service.update(b);
	}
	
	@GetMapping(value = "/board/selectOne/{num}" )
	public BoardDto selectOne(@PathVariable String num) {//검색
		service.countUp(num);
		return service.selectOne(num);
	}



}
