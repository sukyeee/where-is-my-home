package com.mycom.myapp.notice.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.notice.dto.NoticeDto;
import com.mycom.myapp.notice.dto.NoticeParamDto;
import com.mycom.myapp.notice.dto.NoticeResultDto;

public interface NoticeService {
	// 등록
	NoticeResultDto insert(NoticeDto event, MultipartHttpServletRequest request);
	
	// 목록 조회
	NoticeResultDto selectList(NoticeParamDto param);
	NoticeResultDto selectListSearchWord(NoticeParamDto param);
	NoticeResultDto selectListSearchCode(NoticeParamDto param);
	NoticeResultDto selectListSearchWordAndCode(NoticeParamDto param); // 태그 코드 검색
	
	// 상세 조회
	NoticeResultDto selectDetail(NoticeParamDto param);
	
	// 삭제
	NoticeResultDto delete(int noticeId);
	
	// 수정
	NoticeResultDto update(NoticeDto notice, MultipartHttpServletRequest request);
//	int join(NoticeDto notice, UserDto user); // user 테이블 만들고 난 후
}
