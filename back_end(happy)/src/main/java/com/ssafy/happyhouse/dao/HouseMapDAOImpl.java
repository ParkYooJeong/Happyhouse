package com.ssafy.happyhouse.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import com.api.ApiExplorer;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.mapper.HouseMapper;

@Repository
public class HouseMapDAOImpl implements HouseMapDAO {
	@Autowired
	HouseMapper mapper;
	
	
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return mapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return mapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return mapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return mapper.getAptInDong(dong);
	}
	//동에 해당하는 리스트 받아가기
	@Override
	public List<HouseDealDto> getDealInDong(String dong,String apt) throws Exception {
		List<HouseDealDto> al2=new ArrayList<HouseDealDto>() ;
		System.out.println("GET_DEAL_IN_DONG");
		if(dong==null)
			return al2;
		String guguncode=mapper.findGu(dong);
		System.out.println(guguncode);
		List<HouseDealDto> als=getHouses(guguncode);
		
		for (HouseDealDto h:als) {
			if(h.getDong().equals(dong)&&h.getAptName().trim().equals(apt)) {
				al2.add(h);
			}
		}
		System.out.println(al2.size()+"사이즈");
		return al2;
	}

	@Override
	public List<HouseDealDto> getDealInApt(String dong, String apt) throws Exception {
		HouseDealDto d=new HouseDealDto();
		d.setDong(dong);
		d.setAptName(apt);
		return mapper.getDealInApt(d);
	}

	@Override
	public HouseInfoDto getAptDetail(String num) {
		return mapper.getAptDetail(num);
	}

	//지역코드로 api를 사용하여 매매 리스트 받아오기
	@Override
	public List<HouseDealDto> getHouses(String code) {
		ApiExplorer api=new ApiExplorer();
		try {
			api.getHouseDeal(code);
		} catch (IOException | ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return api.getHouseList();
	}
	//해당 아파트 매매 리스트 출력-->지우기
	@Override
	public List<HouseDealDto> getInfoList(String name) {
		List<HouseDealDto> al2=new ArrayList<HouseDealDto>() ;
		for(HouseDealDto d:al2) {
			if(d.getAptName().equals(name))
				al2.add(d);
		}
		return al2;
	}

	@Override
	public List<HouseInfoDto> mapDrag(String south, String west, String north, String east) {
		double s=Double.parseDouble(south);
		double w=Double.parseDouble(west);
		double e=Double.parseDouble(east);
		double n=Double.parseDouble(north);
		List<HouseInfoDto> k=mapper.mapDrag(s,w,e,n);
		System.out.println(k.size()+"size");
		return k; 
	}

	
}
