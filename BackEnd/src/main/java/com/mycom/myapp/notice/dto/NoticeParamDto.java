package com.mycom.myapp.notice.dto;

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
public class NoticeParamDto {
	// 목록
	private int limit;
	private int offset;
	private String searchWord; // 검색어
	private String code; // 카테고리
	
	// 상세
	private int noticeId;
	private int userId;
}
