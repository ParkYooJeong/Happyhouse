package com.api;

import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import org.w3c.dom.Element;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import com.ssafy.happyhouse.dto.HouseDealDto;

import java.io.BufferedReader;
import java.io.IOException;

// 국토교통부_연립다세대 /아파트 매매 실거래자료
public class ApiExplorer {
	private ArrayList<HouseDealDto> al1 = new ArrayList<HouseDealDto>();// 연립다세대
	private ArrayList<HouseDealDto> al2 = new ArrayList<HouseDealDto>();// 연립다세대

	public void getHouseDeal(String code) throws IOException, ParserConfigurationException, SAXException {
//	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {//==> static 변환하기
		// 연립다세대
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade"); /*
																																 * URL
																																 */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=S4Hjqz6er8y%2BLO%2BIXv3wBjlLtBsUWE3KQcBQ1b4I%2BZ3Bh2rMfjL8OazL%2BnSULf16uZysNztCwcYuYidjWW6TvA%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
		urlBuilder.append(
				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8")); /* 각 지역별 코드 11110*/
		urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "="
				+ URLEncoder.encode("201512", "UTF-8")); /* 월 단위 신고자료 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		//System.out.println("Response code: " + conn.getResponseCode());

		// 아파트매매
		StringBuilder urlBuilder1 = new StringBuilder(
				"http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade"); /*
																																 * URL
																																 */
		urlBuilder1.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=S4Hjqz6er8y%2BLO%2BIXv3wBjlLtBsUWE3KQcBQ1b4I%2BZ3Bh2rMfjL8OazL%2BnSULf16uZysNztCwcYuYidjWW6TvA%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
		urlBuilder1.append(
				"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8")); /* 각 지역별 코드 */
		urlBuilder1.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "="
				+ URLEncoder.encode("201512", "UTF-8")); /* 월 단위 신고자료 */
		URL url1 = new URL(urlBuilder1.toString());
		HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
		conn1.setRequestMethod("GET");
		conn1.setRequestProperty("Content-type", "application/json");
		//System.out.println("Response code: " + conn1.getResponseCode());

		al1.clear();
		al2.clear();
		// 연립다세대
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(urlBuilder.toString());

		// root tag
		doc.getDocumentElement().normalize();
		//System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
		// parsing tag
		NodeList nodeList = doc.getElementsByTagName("item");
	//	System.out.println("파싱할 리스트 수 : " + nodeList.getLength());

		// 아파트 매매

		DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
		Document doc1 = dBuilder1.parse(urlBuilder1.toString());

		// root tag
		doc1.getDocumentElement().normalize();
		//System.out.println("Root Element : " + doc1.getDocumentElement().getNodeName());
		// parsing tag
		NodeList nodeList1 = doc1.getElementsByTagName("item");
		//System.out.println("파싱할 리스트 수 : " + nodeList1.getLength());
		
		
		int index = 0;
		
		for (int temp = 0; temp < nodeList.getLength(); temp++) {

			Node nNode = nodeList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;
				HouseDealDto house = new HouseDealDto(index++);

				house.setDealAmount(getTagValue("거래금액", element));
				house.setBuildYear(getTagValue("건축년도", element));
				house.setDealYear(getTagValue("년", element));
				house.setDong(getTagValue("법정동", element).trim());
				house.setAptName(getTagValue("연립다세대", element).trim());
				house.setDealMonth(getTagValue("월", element));
				house.setDealDay(getTagValue("일", element));
				house.setArea(getTagValue("전용면적", element));
				house.setJibun(getTagValue("지번", element));
				house.setCode(getTagValue("지역코드", element));
				house.setFloor(getTagValue("층", element));
				al1.add(house);

			} // if
		} // for
//		System.out.println(al1.size());
//		for (HouseDealDto d : al1) {
//			System.out.println(d.toString());
//		}

		// 아파트 매매

		int index1 = 0;

		for (int temp = 0; temp < nodeList1.getLength(); temp++) {

			Node nNode = nodeList1.item(temp);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) nNode;
				HouseDealDto house = new HouseDealDto(index1++);

				house.setDealAmount(getTagValue("거래금액", element));
				house.setBuildYear(getTagValue("건축년도", element));
				house.setDealYear(getTagValue("년", element));
				house.setDong(getTagValue("법정동", element).trim());
				house.setAptName(getTagValue("아파트", element).trim());
				house.setDealMonth(getTagValue("월", element));
				house.setDealDay(getTagValue("일", element));
				house.setArea(getTagValue("전용면적", element));
//				house.setJibun(getTagValue("지번", element)); 왜 에러나지...
				house.setCode(getTagValue("지역코드", element));
				house.setFloor(getTagValue("층", element));
				al2.add(house);

			} // if
		} // for
//		System.out.println(al2.size());
//		for (HouseDealDto d : al2) {
//			System.out.println(d.toString());
//		}
		conn.disconnect();
		conn1.disconnect();
	}

	public String getTagValue(String tag, Element ele) {
		NodeList nodeList = ele.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nodeList.item(0);
		if (nValue == null) {
			return null;
		}
//		System.out.println(nValue.getNodeValue()+"값");
		return nValue.getNodeValue();
	}// getTagValu

	public ArrayList<HouseDealDto> getHouseList() {
		al1.addAll(al2);
		return al1;
	}

}