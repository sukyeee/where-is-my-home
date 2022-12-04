package com.mycom.myapp.bookmark.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.bookmark.dto.BookmarkDto;
import com.mycom.myapp.bookmark.dto.BookmarkParamDto;

@Mapper
public interface BookmarkDao {

	// 목록 조회
	List<BookmarkDto> selectList(BookmarkParamDto param); // 전체 목록 조회
	int selectListTotalCount(BookmarkParamDto param); // 전체 총 개수

	List<BookmarkDto> selectListFilterDong(BookmarkParamDto param); // 검색어
	int selectListFilterDongTotalCount(BookmarkParamDto param); // 검색어 결과 목록 총 개수
	
	List<String> selectDongList(BookmarkParamDto param); // 전체 행정동 카테고리 조회 

	
	BookmarkDto selectDetail(BookmarkParamDto param); // Q&A 상세 조회

	// 등록
	void insert(BookmarkDto dto);

	// 삭제
	int delete(int bookmarkId);
	
	
}