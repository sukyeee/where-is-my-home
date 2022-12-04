package com.mycom.myapp.event.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResultDto {
	private int result;
	private EventDto dto;
	private List<EventDto> list;
	private int count; // 총 건수
}
