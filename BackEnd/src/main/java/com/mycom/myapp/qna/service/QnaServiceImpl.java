package com.mycom.myapp.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myapp.qna.dao.QnaDao;
import com.mycom.myapp.qna.dto.CommentDto;
import com.mycom.myapp.qna.dto.CommentParamDto;
import com.mycom.myapp.qna.dto.CommentResultDto;
import com.mycom.myapp.qna.dto.QnaDto;
import com.mycom.myapp.qna.dto.QnaParamDto;
import com.mycom.myapp.qna.dto.QnaResultDto;

	
@Service
	public class QnaServiceImpl implements QnaService {
	
	@Autowired
	QnaDao dao;
	/* 업로드 후 upload 폴더 refresh 하거나 preferences / workspace - refresh... 2개 option check */
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;


	@Override
	public QnaResultDto selectList(QnaParamDto param) {
		QnaResultDto resultDto = new QnaResultDto();
		
		try {
			List<QnaDto> list = dao.selectList(param);
			int count = dao.selectListTotalCount();
			resultDto.setList(list);
			resultDto.setCount(count);
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public QnaResultDto selectListSearchWord(QnaParamDto param) {
		QnaResultDto resultDto = new QnaResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<QnaDto> list = dao.selectListSearchWord(param);
			int count = dao.selectListSearchWordTotalCount(param);
			resultDto.setList(list);
			resultDto.setCount(count);
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public QnaResultDto selectDetail(QnaParamDto param) {
		QnaResultDto resultDto = new QnaResultDto();
		try {

			// DB에서 게시글 정보를 가져온다.
			QnaDto dto = dao.selectDetail(param);
			
			// qnaResultDto의 일부인 qnaDto 를 설정
			resultDto.setDto(dto);
			
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}


	@Override
	@Transactional
	public QnaResultDto insert(QnaDto dto) {
	QnaResultDto QnaResultDto = new QnaResultDto();
		
		try {
			dao.insert(dto); // useGeneratedKeys="true" keyProperty="QnaId"
			QnaResultDto.setResult(SUCCESS);

		}catch(Exception e) {
			e.printStackTrace();
			QnaResultDto.setResult(FAIL);
		}
		return QnaResultDto;
	}

	@Override
	public QnaResultDto update(QnaDto Qna) {
		QnaResultDto resultDto = new QnaResultDto();
		try {
			int ret = dao.update(Qna); // 게시글을 수정

			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}
	
	
	@Override
	public QnaResultDto delete(int qnaId) {
		QnaResultDto resultDto = new QnaResultDto();
	    
	    try {
	        dao.delete(qnaId);        
	        resultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        resultDto.setResult(FAIL);
	    }
	    
	    return resultDto;
	}

	@Override
	public CommentResultDto commentList(CommentParamDto param) {
		
		
		CommentResultDto resultDto = new CommentResultDto();
		
		try {
			List<CommentDto> list = dao.commentList(param);
			
			
//			int count = dao.selectListTotalCount();
			int count = 0;
			resultDto.setCommentList(list);
			resultDto.setCount(count);
			resultDto.setResult(SUCCESS);
			
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
		
	}
	
	@Override
	public CommentResultDto commentInsert(CommentDto dto) {
		CommentResultDto commentResultDto = new CommentResultDto();
		
		try {
			dao.commentInsert(dto);

			commentResultDto.setResult(SUCCESS);

		}catch(Exception e) {
			e.printStackTrace();
			commentResultDto.setResult(FAIL);
		}
		return commentResultDto;
	}
	
	@Override
	public CommentResultDto replyList(CommentParamDto param) {
		
		
		CommentResultDto resultDto = new CommentResultDto();
		
		try {
			List<CommentDto> list = dao.replyList(param);
//			resultDto.setCommentList(list);
			resultDto.setReplyList(list);
			resultDto.setResult(SUCCESS);
			
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
		
	}

	@Override
	public CommentResultDto commentUpdate(CommentDto Qna) {
		CommentResultDto resultDto = new CommentResultDto();
		try {
			int ret = dao.commentUpdate(Qna); // 게시글을 수정

			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}
	
	@Override
	public CommentResultDto commentDelete(int commentId) {
		CommentResultDto resultDto = new CommentResultDto();
	    
	    try {
	        dao.commentDelete(commentId);        
	        resultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        resultDto.setResult(FAIL);
	    }
	    
	    return resultDto;
	}
	



}