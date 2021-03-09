package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.dto.MemberDto;

public interface MemberDAO {
	public MemberDto check(MemberDto m) throws Exception;

	public void insert(MemberDto m) throws SQLException;

	public ArrayList<MemberDto> select();

	public void selectOne(String id);

	public void update(MemberDto m);

	public ArrayList<MemberDto> search(String option, String name);
}
