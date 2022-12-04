package com.mycom.myapp.event.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.event.dto.EventDto;
import com.mycom.myapp.event.dto.EventParamDto;
import com.mycom.myapp.event.dto.EventResultDto;

public interface EventService {
	// 등록
	EventResultDto insert(EventDto event, MultipartHttpServletRequest request);
	EventResultDto insertUserJoin(EventParamDto param); // 사용자의 이벤트 참가
	
	// 목록 조회
	EventResultDto selectList(EventParamDto param);
	EventResultDto selectListSearchWord(EventParamDto param);
	EventResultDto selectListSearchCode(EventParamDto param);
	
	// 상세 조회
	EventResultDto selectDetail(EventParamDto param);
	
	// 삭제
	EventResultDto delete(int eventId);
	EventResultDto deleteUserJoinByUserIdAndEventId(EventParamDto param);
	
	// 수정
	EventResultDto update(EventDto event, MultipartHttpServletRequest request);
}
