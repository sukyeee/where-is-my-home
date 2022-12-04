package com.mycom.myapp.qna.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentResultDto {	
	
	// 댓글
	private int result;
	private CommentDto commentDto;
	private List<CommentDto> commentList;
	private int count;
	
	// 답글
	private List<CommentDto> replyList;
	

}

