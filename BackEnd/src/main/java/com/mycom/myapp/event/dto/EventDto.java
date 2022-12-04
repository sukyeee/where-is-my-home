package com.mycom.myapp.event.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class EventDto {
	private int eventId;
	private int userId;
	private String code;
	private String title;
	private String content;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime registerDate;
	private int readCount;
	
	private List<EventFileDto> fileList;
	// 목록 조회 시, 썸네일 용 첫번째 파일 or 대체 이미지
	private EventFileDto thumbnailFile;
	// 목록&상세 조회 시, 유저 참여 신청 여부
	private boolean isJoined;
	
	public void setStartDate(String startDate) {
		this.startDate = LocalDateTime.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	public void setEndDate(String endDate) {
		this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
