package com.mycom.myapp.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myapp.event.dto.EventDto;
import com.mycom.myapp.event.dto.EventFileDto;
import com.mycom.myapp.event.dto.EventParamDto;
import com.mycom.myapp.user.dto.UserDto;

@Mapper
public interface EventDao {
	// 등록
	int insert(EventDto event);
	int insertFile(EventFileDto dto); // 첨부파일 등록
	int insertUserJoin(EventParamDto dto); // 이벤트 참가 // user 테이블 만들고 난 후
	
	// 삭제
	int delete(int eventId);
	List<String> selectfileUrlDeleteList(int eventId); // 삭제할 물리 첨부파일 리스트
	void deleteFile(int eventId); // 첨부파일 삭제
	void deleteUserRead(int eventId); // 조회 수 삭제
	void deleteUserJoinByUserIdAndEventId(EventParamDto param); // 이벤트 참가 기록 삭제 -> 유저 이벤트 취소할 때
	void deleteUserJoinByUserId(int userId); // (유저 아이디 기준) 이벤트 참가 기록 삭제 -> 유저 탈퇴할 때
	void deleteUserJoinByEventId(int eventId); // (이벤트 아이디 기준) 이벤트 참가 기록 삭제 -> 이벤트가 삭제될 때
	
	// 수정
	int update(EventDto event);

	// 목록 조회
	List<EventDto> selectList(EventParamDto param); // 전체 목록 조회
	int selectListTotalCount(); // 전체 총 개수
	
	List<EventDto> selectListSearchWord(EventParamDto param); // 검색어
	int selectListSearchWordTotalCount(EventParamDto param); // 검색어 결과 목록 총 개수
	
	List<EventDto> selectListSearchCode(EventParamDto param); // 태그 코드 검색
	int selectListSearchCodeTotalCount(EventParamDto param); // 태그 코드 검색 결과 목록 총 개수
	
	// 상세 조회
	EventDto selectDetail(EventParamDto param); // 이벤트 상세 조회
	List<EventFileDto> selectDetailFileList(int eventId); // 첨부파일 리스트
	
	// 참여 신청 여부 조회
	int selectUserJoin(@Param("eventId") int eventId, @Param("userId") int userId);
	
	// 조회수
	int selectUserReadCount(EventParamDto eventParamDto); // 이벤트 조회수 조회
	int insertUserRead(@Param("eventId") int eventId, @Param("userId") int userId); // 이벤트 조회 기록 추가 (사용자-이벤트)
	int updateEventReadCount(int eventId); // 조회수 증가	
}
