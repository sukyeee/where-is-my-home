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
public class HouseReviewDto {
	private int reviewId;
	private String houseId;
	private int userId;
	private boolean sameUser;

	private String userNickname;
	private String userScore;
	private String content;
	private int score;
	private Date registerDate;

	private boolean isEditOpen;

}
