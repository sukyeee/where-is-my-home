package com.mycom.myapp.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myapp.notice.dto.NoticeDto;
import com.mycom.myapp.notice.dto.NoticeFileDto;
import com.mycom.myapp.notice.dto.NoticeParamDto;


@Mapper
public interface NoticeDao {
	// 등록
	int insert(NoticeDto notice);
	int insertFile(NoticeFileDto dto); // 첨부파일 등록
	
	// 삭제
	int delete(int noticeId);
	List<String> selectfileUrlDeleteList(int noticeId); // 삭제할 물리 첨부파일 리스트
	void deleteFile(int noticeId); // 첨부파일 삭제
	void deleteUserRead(int noticeId); // 조회 수 삭제
	
	// 수정
	int update(NoticeDto notice);

	// 목록 조회
	List<NoticeDto> selectList(NoticeParamDto param); // 전체 목록 조회
	int selectListTotalCount(); // 전체 총 개수
	
	List<NoticeDto> selectListSearchWord(NoticeParamDto param); // 검색어
	int selectListSearchWordTotalCount(NoticeParamDto param); // 검색어 결과 목록 총 개수
	
	List<NoticeDto> selectListSearchCode(NoticeParamDto param); // 태그 코드 검색
	int selectListSearchCodeTotalCount(NoticeParamDto param); // 태그 코드 검색 결과 목록 총 개수

	List<NoticeDto> selectListSearchWordAndCode(NoticeParamDto param); // 태그 코드 검색
	int selectListSearchWordAndCodeTotalCount(NoticeParamDto param); // 태그 코드 검색 결과 목록 총 개수
	
	// 상세 조회
	NoticeDto selectDetail(NoticeParamDto param); // 이벤트 상세 조회
	List<NoticeFileDto> selectDetailFileList(int noticeId); // 첨부파일 리스트
	
	// 조회수
	int selectUserReadCount(NoticeParamDto NoticeParamDto); // 이벤트 조회수 조회
	int insertUserRead(@Param("noticeId") int noticeId, @Param("userId") int userId); // 이벤트 조회 기록 추가 (사용자-이벤트)
	int updateNoticeReadCount(int noticeId); // 조회수 증가	
}
