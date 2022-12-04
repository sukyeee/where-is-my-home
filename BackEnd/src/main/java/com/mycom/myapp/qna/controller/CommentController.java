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

import com.mycom.myapp.qna.dto.CommentDto;
import com.mycom.myapp.qna.dto.CommentParamDto;
import com.mycom.myapp.qna.dto.CommentResultDto;
import com.mycom.myapp.qna.dto.QnaDto;
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
@RequestMapping("/comments")
 public class CommentController {

 @Autowired
 QnaService service;

 private final int SUCCESS = 1;
 private final int FAIL = -1;


		@GetMapping("")
		private ResponseEntity<CommentResultDto> commentList(CommentParamDto param){
			
			CommentResultDto resultDto = service.commentList(param);
			
			if(resultDto.getResult() == SUCCESS) {
				return new ResponseEntity<>(resultDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
			}
		}
		
		// 댓글 추가 
		 @PostMapping("")
		 private ResponseEntity<CommentResultDto> commentInsert(@RequestBody CommentDto commentDto, HttpSession session) {
		
			 
			 commentDto.setUserId(((UserDto) session.getAttribute("userDto")).getUserId());
		

			 CommentResultDto commentResultDto = service.commentInsert(commentDto);
		
			 if (commentResultDto.getResult() == SUCCESS) {
				 return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.OK);
			 } else {
				 return new ResponseEntity<CommentResultDto>(commentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
			 }
			
		 }
		 
		 	@PutMapping("/{commentId}")
			private ResponseEntity<CommentResultDto> commentUpdate(@RequestBody CommentDto dto, @PathVariable int commentId){
			
			 	dto.setCommentId(commentId);
			 	CommentResultDto resultDto = service.commentUpdate(dto);
			 	
				if(resultDto.getResult() == SUCCESS) {
					return new ResponseEntity<>(resultDto, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
				}
				
			}
		 	
			@DeleteMapping("/{commentId}")
			private ResponseEntity<CommentResultDto> delete(@PathVariable int commentId){
				CommentResultDto resultDto = service.commentDelete(commentId);
				
				if(resultDto.getResult() == SUCCESS) {
					return new ResponseEntity<>(resultDto, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
				}
			}
		 	
		 	
			@GetMapping("/{replyId}")
			private ResponseEntity<CommentResultDto> replyList(CommentParamDto param){
				
				System.out.println("reply List:!!!!!!! " + param);
				CommentResultDto resultDto = service.replyList(param);
				
				if(resultDto.getResult() == SUCCESS) {
					return new ResponseEntity<>(resultDto, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
				}
			}
		

 	}