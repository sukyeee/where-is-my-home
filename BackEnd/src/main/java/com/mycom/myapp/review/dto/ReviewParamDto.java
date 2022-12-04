package com.mycom.myapp.review.dto;


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
public class ReviewParamDto {
	private int limit = 5;
	private int offset;

	// 조회
	private String houseId;
	private String dongCode;

	// 등록
	private int userId;

	// 삭제
	private int reviewId;

}
