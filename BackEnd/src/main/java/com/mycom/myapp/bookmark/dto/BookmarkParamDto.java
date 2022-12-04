package com.mycom.myapp.bookmark.dto;

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
public class BookmarkParamDto {
	// 목록
	private int limit;
	private int offset;
	private String dong; // 동 필터
	
	// 상세
	private int bookmarkId;
	private int userId;
}
