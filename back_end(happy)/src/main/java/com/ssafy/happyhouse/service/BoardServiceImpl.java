package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.BoardDAO;
import com.ssafy.happyhouse.dto.BoardDto;
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO dao;

	@Override
	public List<BoardDto> selectAll() {
		return dao.selectAll();
	}

	@Override
	public void delete(String num) {
		dao.delete(num);
	}

	@Override
	public List<BoardDto> search(String word) {
		return dao.search(word);
	}

	@Override
	public void insert(BoardDto b) {
		dao.insert(b);
	}

	@Override
	public void update(BoardDto b) {
		dao.update(b);
	}

	@Override
	public BoardDto selectOne(String num) {
		return dao.selectOne(num);
	}

	@Override
	public void countUp(String num) {
		dao.countUp(num);
	}

	@Override
	public List<BoardDto> searchTitle(String word) {
		// TODO Auto-generated method stub
		return dao.searchTitle(word);
	}


}
