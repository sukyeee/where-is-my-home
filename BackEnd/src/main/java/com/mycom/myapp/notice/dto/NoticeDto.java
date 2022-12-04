package com.mycom.myapp.notice.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoticeDto {
	private int noticeId;
	private int userId;
	private String userName; // 상세 조회 시, 작성자 이름
	private String title;
	private String content;
	private LocalDateTime registerDate;
	private int readCount;
	private String code;

	private List<NoticeFileDto> fileList;
}
