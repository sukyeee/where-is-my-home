package com.mycom.myapp.house.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycom.myapp.house.dto.NewsResultDto;


@Service
public class SearchNewsServiceImpl implements SearchNewsService {

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@Value("${app.naver.client.id}")
	private String CLIENT_ID;

	@Value("${app.naver.client.secret}")
	private String CLIENT_SECRET;


	@Override
	public NewsResultDto crawlingNews(String sido, String dong) {
		String clientId = CLIENT_ID; // 애플리케이션 클라이언트 아이디
		String clientSecret = CLIENT_SECRET; // 애플리케이션 클라이언트 시크릿
		System.out.println("KEY: " + clientId + " " + clientSecret);

		String text = null;
		try {
			text = URLEncoder.encode(sido + " " + dong, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text + "&sort=sim"; // JSON
		                                                                                        // 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL, requestHeaders);

		// 검색 결과 json 데이터를 Dto로 역직렬화하기
		NewsResultDto resultDto = new NewsResultDto();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			resultDto = objectMapper.readValue(responseBody, NewsResultDto.class);
			resultDto.setResult(SUCCESS);
			System.out.println("Deserialization 성공!!");
			// System.out.println(resultDto);
		} catch (Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
			System.out.println("Deserialization 실패.....");
		}

		return resultDto;
	}


	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 오류 발생
				return readBody(con.getErrorStream());
			}

		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}

	}


	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}

	}


	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
		}

	}
}
