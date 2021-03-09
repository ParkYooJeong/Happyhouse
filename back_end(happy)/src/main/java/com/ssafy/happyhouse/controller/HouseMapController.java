package com.ssafy.happyhouse.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.NewsDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HouseMapService;

@RestController
@CrossOrigin
public class HouseMapController {

	@Autowired
	HouseMapService service;// interface type

	// 지역별 매매 내역
//	@RequestMapping(value = "/map/gethouses/{code}")
//	public List<HouseDealDto> getHouses(HttpServletRequest request, HttpServletResponse response,@PathVariable String code) throws Exception {
//		return service.getHouses(code);
//	}

	// select 시도
	@GetMapping(value = "/map/sido")
	public List<SidoGugunCodeDto> sido(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		return service.getSido();
	}

	// select 구군
	@GetMapping(value = "/map/gugun/{sido}")
	public List<SidoGugunCodeDto> gugun(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String sido) throws Exception {
		// String sido = request.getParameter("sido");
		response.setContentType("text/html;charset=UTF-8");
		return service.getGugunInSido(sido);
	}

	// select 동
	@GetMapping(value = "/map/dong/{gugun}")
	public List<HouseInfoDto> dong(HttpServletRequest request, HttpServletResponse response, @PathVariable String gugun)
			throws Exception {
//		String gugun = request.getParameter("gugun");
		response.setContentType("text/html;charset=UTF-8");
		return service.getDongInGugun(gugun);
	}

	@GetMapping(value = "/map/apt/{dong}")
	public List<HouseInfoDto> apt(HttpServletRequest request, HttpServletResponse response, @PathVariable String dong)
			throws Exception {
//		String dong = request.getParameter("dong");
		response.setContentType("text/html;charset=UTF-8");
		return service.getAptInDong(dong);
	}

	@GetMapping(value = "/map/deal_dong/{dong}&{aptName}")
	public List<HouseDealDto> deal_dong(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String dong,@PathVariable String aptName) throws Exception {
//		String dong = request.getParameter("dong");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(dong);
		return service.getDealInDong(dong,aptName);
	}

	@GetMapping(value = "/map/deal_apt/{dong}/{apt}")
	public List<HouseDealDto> deal_apt(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String dong, @PathVariable String apt) throws Exception {
//		String dong = request.getParameter("dong");
//		String apt = request.getParameter("apt");p;.
		response.setContentType("text/html;charset=UTF-8");
		return service.getDealInApt(dong, apt);
	}

	@GetMapping(value = "/map/aptDetail/{num}")
	public HouseInfoDto deal_apt(@PathVariable String num) throws Exception {
		return service.getAptDetail(num);
	}

	@GetMapping(value = "/news")
	public ArrayList<NewsDto> getNews() throws Exception {
		return service.getNews();
	}

	// 지역별 매매내역에서 해당 아파트 매물 내역만 가져오기
	@GetMapping(value = "/map/getInfo/{name}")
	public List<HouseDealDto> getInfoList(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String name) throws Exception {
		return service.getInfoList(name);
	}

	// 위도 경도 지도 범위에 해당하는 리스트 가져오기(지도 드래그 이벤트)
	@PostMapping(value = "/map/getmap")
	public List<HouseInfoDto> mapDrag(@RequestBody String swLatLng) throws Exception {
		String[] arr = swLatLng.split("[(), ]");
		
		return service.mapDrag(arr[1], arr[3], arr[5], arr[7]);
	}

}