package com.ssafy.happyhouse.dto;

public class MemberDto {
	String no;
	String name;
	String id;
	String pass;
	String tel;
	
	
	
	public MemberDto() {
		super();
	}


	public MemberDto( String name,String id, String pass, String tel) {
		super();
		
		this.name = name;
		this.id = id;
		this.pass = pass;
		this.tel = tel;
	}
	

	public MemberDto(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}


	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
