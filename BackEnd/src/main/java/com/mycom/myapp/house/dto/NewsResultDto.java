package com.mycom.myapp.house.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewsResultDto {
	private Date lastBuildDate; //검색 결과를 생성한 시간
	private int total; //총 검색 결과 개수
	private int start; //검색 시작 위치
	private int display; // 한 번에 표시할 검색 결과 개수
	private List<NewsDto> items; // 검색 결과
	
	private int result;
}
