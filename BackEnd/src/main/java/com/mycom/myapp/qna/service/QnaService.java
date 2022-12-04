package com.mycom.myapp.qna.service;

import com.mycom.myapp.qna.dto.CommentDto;
import com.mycom.myapp.qna.dto.CommentParamDto;
import com.mycom.myapp.qna.dto.CommentResultDto;
import com.mycom.myapp.qna.dto.QnaDto;
import com.mycom.myapp.qna.dto.QnaParamDto;
import com.mycom.myapp.qna.dto.QnaResultDto;

public interface QnaService {

	
	// 게시글 목록 조회
	QnaResultDto selectList(QnaParamDto param);
	QnaResultDto selectListSearchWord(QnaParamDto param);

	// 상세 조회
	QnaResultDto selectDetail(QnaParamDto param);
		
	// 등록
	QnaResultDto insert(QnaDto dto);

	// 수정
	QnaResultDto update(QnaDto dto);
	
	// 삭제
	QnaResultDto delete(int qnaId);
	
	// 댓글 목록 조회
	CommentResultDto commentList(CommentParamDto param);
	
	// 댓글 등록
	CommentResultDto commentInsert(CommentDto dto);

	// 댓글 수정
	CommentResultDto commentUpdate(CommentDto dto);
		
	// 댓글 삭제
	CommentResultDto commentDelete(int commentId);
	
	// 답글 목록 조회
	CommentResultDto replyList(CommentParamDto param);


}