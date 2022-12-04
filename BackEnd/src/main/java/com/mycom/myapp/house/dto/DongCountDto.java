package com.mycom.myapp.house.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DongCountDto {
	private String dongCode;
	private String gugunName;
	private int houseCount;
}
