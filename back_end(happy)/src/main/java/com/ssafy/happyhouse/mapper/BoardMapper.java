package com.ssafy.happyhouse.mapper;

import java.util.List;

import com.ssafy.happyhouse.dto.BoardDto;

public interface BoardMapper {
	public List<BoardDto> selectAll();
	public void delete(String num);
	public List<BoardDto> search(String word);
	public void insert(BoardDto b);
	public void update(BoardDto b);
	public BoardDto selectOne(String num);
	public void countUp(String num);
	public List<BoardDto> searchTitle(String word);
	
}
