package com.ssafy.happyhouse.mapper;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface HouseMapper {
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
	// 동 별 실거래가 정보
	List<HouseDealDto> getDealInDong(String dong) throws Exception;
	// 아파트 별 실거리가 정보
	List<HouseDealDto> getDealInApt(HouseDealDto d) throws Exception;
	HouseInfoDto getAptDetail(String num);
	//동아름으로 구군 코드 찾기
	String findGu(String num);
	List<HouseInfoDto> mapDrag(double south, double west, double east, double north);
}
