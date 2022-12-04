 package com.mycom.myapp.qna.controller;


 import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.qna.dto.CommentParamDto;
import com.mycom.myapp.qna.dto.CommentResultDto;
import com.mycom.myapp.qna.dto.QnaDto;
import com.mycom.myapp.qna.dto.QnaParamDto;
import com.mycom.myapp.qna.dto.QnaResultDto;
import com.mycom.myapp.qna.service.QnaService;
import com.mycom.myapp.user.dto.UserDto;

@RestController
@CrossOrigin(
		origins="http://localhost:5500",
		allowCredentials="true",
		allowedHeaders="*",
		methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
				RequestMethod.OPTIONS, RequestMethod.HEAD}
)
@RequestMapping("/qnas")
 public class QnaController {

 @Autowired
 QnaService service;

 private final int SUCCESS = 1;
 private final int FAIL = -1;


 	@GetMapping("")
	// searchWord, category 유무에 따라 분리해서 처리
	private ResponseEntity<QnaResultDto> selectList(QnaParamDto param){
		QnaResultDto resultDto;
		
		if(!param.getSearchWord().isEmpty() ) {
			resultDto = service.selectListSearchWord(param);
		} 
		else {
			resultDto = service.selectList(param);			
		}
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
 	
	@GetMapping("/{qnaId}")
	private ResponseEntity<QnaResultDto> selectDetail(@PathVariable int qnaId, HttpSession session){
		
		
		QnaParamDto param = new QnaParamDto();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		param.setQnaId(qnaId); // PathVariable 로 넘어온 게시글 key
		
		QnaResultDto resultDto = service.selectDetail(param);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	 @PostMapping("")
	 private ResponseEntity<QnaResultDto> qnaInsert(@RequestBody QnaDto qnaDto, HttpSession session) {
	
		 qnaDto.setUserId( ((UserDto) session.getAttribute("userDto")).getUserId());

		 QnaResultDto qnaResultDto = service.insert(qnaDto);
	
		 if (qnaResultDto.getResult() == SUCCESS) {
			 return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.OK);
		 } else {
			 return new ResponseEntity<QnaResultDto>(qnaResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		
	 }
	 
	 	@PutMapping("/{qnaId}")
		private ResponseEntity<QnaResultDto> update(@RequestBody QnaDto dto){
		
		 
		 	QnaResultDto resultDto = service.update(dto);
			
			if(resultDto.getResult() == SUCCESS) {
				return new ResponseEntity<>(resultDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
			}
			
		}
	 
		@DeleteMapping("/{qnaId}")
		private ResponseEntity<QnaResultDto> delete(@PathVariable int qnaId){
			QnaResultDto resultDto = service.delete(qnaId);
			
			if(resultDto.getResult() == SUCCESS) {
				return new ResponseEntity<>(resultDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
			}
		}
		
	 

 	}