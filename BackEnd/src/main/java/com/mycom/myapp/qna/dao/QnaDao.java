package com.mycom.myapp.qna.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.qna.dto.CommentDto;
import com.mycom.myapp.qna.dto.CommentParamDto;
import com.mycom.myapp.qna.dto.QnaDto;
import com.mycom.myapp.qna.dto.QnaParamDto;

@Mapper
public interface QnaDao {

	// 목록 조회
	List<QnaDto> selectList(QnaParamDto param); // 전체 목록 조회
	int selectListTotalCount(); // 전체 총 개수

	List<QnaDto> selectListSearchWord(QnaParamDto param); // 검색어
	int selectListSearchWordTotalCount(QnaParamDto param); // 검색어 결과 목록 총 개수
	
	QnaDto selectDetail(QnaParamDto param); // Q&A 상세 조회

	// 등록
	void insert(QnaDto dto);

	// 수정
	int update(QnaDto dto);

	// 삭제
	int delete(int qnaId);
	
	
	// 댓글 목록 조회
	List<CommentDto> commentList(CommentParamDto comment); // 전체 목록 조회
	// 댓글 등록
	void commentInsert(CommentDto dto);
	// 댓글 수정
	int commentUpdate(CommentDto dto);
	// 댓글 삭제
	int commentDelete(int commentId);
		
	// 답글 목록 조회
	List<CommentDto> replyList(CommentParamDto comment); // 전체 목록 조회
	

	
}