package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.dto.MemberDto;

public interface MemberMapper {
	public MemberDto check(MemberDto m) throws SQLException;

	public void insert(MemberDto m) throws SQLException;

	public ArrayList<MemberDto> select() ;

	public void selectOne(String id) ;

	public void update(MemberDto m);

	public ArrayList<MemberDto> searchId(String name);

	public ArrayList<MemberDto> searchName(String name) ;
}
