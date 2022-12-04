package com.mycom.myapp.review.dto;


import java.util.Date;

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
public class DongReviewDto {
	private int reviewId;
	private String dongCode;
	private int userId;
	private int score;
	private Date registerDate;

}
