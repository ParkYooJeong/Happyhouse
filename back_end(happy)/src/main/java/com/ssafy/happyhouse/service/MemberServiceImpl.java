package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.MemberDAO;
import com.ssafy.happyhouse.dto.MemberDto;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO dao;

	@Override
	public MemberDto check(MemberDto m) throws Exception{
		return dao.check(m);
	}

	@Override
	public void insert(MemberDto m) throws SQLException{
		// TODO Auto-generated method stub
		dao.insert(m);
	}

	@Override
	public ArrayList<MemberDto> selectAll() {
		// TODO Auto-generated method stub
		return dao.select();
	}

	@Override
	public void selectOne(String id) {
		dao.selectOne(id);
	}

	@Override
	public void update(MemberDto m) {
		dao.update(m);
		
	}

	@Override
	public ArrayList<MemberDto> search(String option, String name) {
		// TODO Auto-generated method stub
		return dao.search(option,name);
	}

}
