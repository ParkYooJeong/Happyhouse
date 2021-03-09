package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.dto.MemberDto;

public interface MemberService {

	public MemberDto check(MemberDto m) throws Exception;
	// TODO Auto-generated method stub

	public void insert(MemberDto m) throws SQLException;

	public ArrayList<MemberDto> selectAll();

	public void selectOne(String id);

	public void update(MemberDto m);

	public ArrayList<MemberDto> search(String option, String name);

}
