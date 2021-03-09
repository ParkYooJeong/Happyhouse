package com.ssafy.happyhouse.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.HouseMapDAO;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.NewsDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	@Autowired
	HouseMapDAO dao;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return dao.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return dao.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return dao.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return dao.getAptInDong(dong);
	}

	@Override
	public List<HouseDealDto> getDealInDong(String dong,String apt) throws Exception {
		return dao.getDealInDong(dong,apt);
	}

	@Override
	public List<HouseDealDto> getDealInApt(String dong, String apt) throws Exception {
		return dao.getDealInApt(dong, apt);
	}

	@Override
	public HouseInfoDto getAptDetail(String num) {
		return dao.getAptDetail(num);
	}

	@Override
	public ArrayList<NewsDto> getNews() throws IOException {
		String URL = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=%EB%B6%80%EB%8F%99%EC%82%B0";
		Document doc = Jsoup.connect(URL).get();
		// System.out.println(doc.html());
		Elements elem = doc.select("div.news_wrap");

		ArrayList<NewsDto> news = new ArrayList<>();
		
		for (int i = 0; i < 6; i++) {
			Element e = elem.get(i);
			Elements title = e.select("a.news_tit");
			Elements content = e.select("a.api_txt_lines");
			Elements image = e.select("a.dsc_thumb");
			news.add(new NewsDto(title.text(), content.text(),title.attr("href") ,image.select("img").attr("src")));
		}
		return news;
	}

//	@Override
//	public List<HouseDealDto> getHouses(String code) {
//		return dao.getHouses(code);
//	}

	@Override
	public List<HouseDealDto> getInfoList(String name) {
		// TODO Auto-generated method stub
		return dao.getInfoList(name);
	}

	@Override
	public List<HouseInfoDto> mapDrag(String south, String west, String east, String north) {
		// TODO Auto-generated method stub
		return dao.mapDrag(south,west,east,north);
	}
}
