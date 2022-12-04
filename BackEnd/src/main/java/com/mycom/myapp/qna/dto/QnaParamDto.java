package com.mycom.myapp.qna.dto;

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
public class QnaParamDto {
	// 목록
	private int limit;
	private int offset;
	private String searchWord; // 검색어
	private String code; // 카테고리
	
	// 상세
	private int qnaId;
	private int userId;
}
