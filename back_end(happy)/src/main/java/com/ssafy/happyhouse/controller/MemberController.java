package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.MemberService;

@RestController
@CrossOrigin
public class MemberController {
	@Autowired
	MemberService service;// interface type
	@Autowired
	private JwtService jwtService;
	
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);


	// 로그인 정보(id)를 세션에 저장함
	@PostMapping(value = "/member/login")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto, HttpServletResponse response, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println(memberDto.getId());
		HttpStatus status = null;
		System.out.println(memberDto.getPass());
		try {
			MemberDto loginUser = service.check(memberDto);
			
			if(loginUser != null) {
//				jwt.io에서 확인
//				로그인 성공했다면 토큰을 생성한다.
				String token = jwtService.create(loginUser);
				logger.trace("로그인 토큰정보 : {}", token);
				
//				토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
//				response.setHeader("auth-token", token);
				resultMap.put("auth-token", token);
				resultMap.put("user-id", loginUser.getId());
				resultMap.put("user-name", loginUser.getName());
				System.out.println(loginUser.getName());
//				resultMap.put("status", true);
//				resultMap.put("data", loginUser);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/member/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			// 사용자에게 전달할 정보이다.
//			String info = memberService.getServerInfo();
			
			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
//			
//			resultMap.put("status", true);
//			resultMap.put("info", info);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping(value = "/member/logout")
	public void logout(HttpServletRequest request) {
		// 세션 얻기
		HttpSession session = request.getSession();
		session.setAttribute("id", null);

		// 초기화면으로 redirect
	}

	@PutMapping(value = "/member/signup")
	@ResponseBody
	public void signup(@RequestBody MemberDto m) throws SQLException  {
		service.insert(m);
	}

	@GetMapping(value = "/member/management")
	public ArrayList<MemberDto> management(HttpServletRequest req) {//회원관리 리스트 출력
		System.out.println("회원관리");
		return service.selectAll();
	}

	@DeleteMapping(value = "/member/delete/{id}")
	public void read(@PathVariable String id) {//회원삭제
		service.selectOne(id);		
	}

	@PutMapping(value = "/member/update")
	@ResponseBody
	public void update(@RequestBody MemberDto m) {//회원 수정
		System.out.println(m.getName()+"nam");
		System.out.println(m.getId());
		System.out.println(m.getPass());
		service.update(m); 
	}

	@GetMapping(value = "/member/search/{option}&&{content}" )
	public ArrayList<MemberDto> search(@PathVariable String option,@PathVariable String content) {//검색
		System.out.println(option+"");
		return service.search(option, content);
	}

}
