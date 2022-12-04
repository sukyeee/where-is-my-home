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
public class QnaResultDto {
	
	// Q&A
	private int result;
	private QnaDto dto;
	private List<QnaDto> list;
	private int count;

}