package com.mycom.myapp.bookmark.service;

import com.mycom.myapp.bookmark.dto.BookmarkDto;
import com.mycom.myapp.bookmark.dto.BookmarkParamDto;
import com.mycom.myapp.bookmark.dto.BookmarkResultDto;

public interface BookmarkService {

	
	// 게시글 목록 조회
	BookmarkResultDto selectList(BookmarkParamDto param);
	BookmarkResultDto selectListFilterDong(BookmarkParamDto param);

	// 상세 조회
	BookmarkResultDto selectDetail(BookmarkParamDto param);
		
	// 등록
	BookmarkResultDto insert(BookmarkDto dto);

	// 삭제
	BookmarkResultDto delete(int bookmarkId);
	
	
}