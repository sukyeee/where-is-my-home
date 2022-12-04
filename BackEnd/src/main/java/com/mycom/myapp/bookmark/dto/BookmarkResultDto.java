package com.mycom.myapp.bookmark.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookmarkResultDto {
	
	private int result;
	private BookmarkDto dto;
	private List<BookmarkDto> list;
	private int count;

	private List<String> dongList; // 행정동만 가져오는 리스트 
	private List<Integer> checkedList; // 비교하기로 선택된 리스트


}