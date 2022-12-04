package com.mycom.myapp.qna.dto;

import java.time.LocalDateTime;

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
public class CommentDto {	
	
	private int commentId;
	private int userId;
	private String userName;
	private int qnaId;
	private int parentCommentId;
	private String content;
	private LocalDateTime registerDate;
	
}

