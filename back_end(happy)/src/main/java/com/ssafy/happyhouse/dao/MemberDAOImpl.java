package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.mapper.MemberMapper;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	MemberMapper mapper;


	@Override
	public MemberDto check(MemberDto m) throws Exception{
		MemberDto login = mapper.check(m);
		return login;
	}

	@Override
	public void insert(MemberDto m) throws SQLException{
		mapper.insert(m);
	}

	@Override
	public ArrayList<MemberDto> select() {
		return mapper.select();
	}

	@Override
	public void selectOne(String id) {// 회원삭제기능임
		mapper.selectOne(id);
	}

	@Override
	public void update(MemberDto m) {
		mapper.update(m);
	}

	@Override
	public ArrayList<MemberDto> search(String option, String name) {
		
		name = "%" + name + "%";
		
		if (option.equals("id"))
			return mapper.searchId(name);
		else
			return mapper.searchName(name);

	}

}
