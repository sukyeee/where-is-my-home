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
public class QnaDto {	
	
	private int qnaId;
	private int userId;
	private String title;
	private String content;
	private String checkYn;
	private LocalDateTime registerDate;
	
}

