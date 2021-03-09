package com.ssafy.happyhouse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.mapper.BoardMapper;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	BoardMapper mapper;

	@Override
	public List<BoardDto> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public void delete(String num) {
		mapper.delete(num);
	}
	@Override
	public List<BoardDto> search(String word) {
		word = "%" + word + "%";
		return mapper.search(word);
	}

	@Override
	public void insert(BoardDto b) {
		mapper.insert(b);
	}

	@Override
	public void update(BoardDto b) {
		mapper.update(b);
	}

	@Override
	public BoardDto selectOne(String num) {
		return mapper.selectOne(num);
	}

	@Override
	public void countUp(String num) {
		mapper.countUp(num);
	}

	@Override
	public List<BoardDto> searchTitle(String word) {
		// TODO Auto-generated method stub
		word="%"+word+"%";
		return mapper.searchTitle(word);
	}


}
