package com.mycom.myapp.notice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResultDto {
	private int result;
	private NoticeDto dto;
	private List<NoticeDto> list;
	private int count; // 총 건수
}
