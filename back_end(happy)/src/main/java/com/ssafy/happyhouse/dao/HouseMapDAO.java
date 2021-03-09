package com.ssafy.happyhouse.dao;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface HouseMapDAO {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	// 동 별 실거래가 정보
	List<HouseDealDto> getDealInDong(String dong, String apt) throws Exception;
	// 아파트 별 실거리가 정보
	List<HouseDealDto> getDealInApt(String dong, String apt) throws Exception;
	HouseInfoDto getAptDetail(String num);
	List<HouseDealDto> getHouses(String code);
	List<HouseDealDto> getInfoList(String name);
	List<HouseInfoDto> mapDrag(String south, String west, String east, String north);
}
