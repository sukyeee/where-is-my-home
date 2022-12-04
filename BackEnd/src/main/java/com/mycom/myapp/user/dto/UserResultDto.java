package com.mycom.myapp.user.dto;

import java.util.Date;
import java.util.List;

import com.mycom.myapp.qna.dto.QnaDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserResultDto {
	private int userId;
	private String userName;
	private String userNickname;
	private String userPassword;
	private String userEmail;
	private Date userRegisterDate;
	private String code;
	private String userProfileImageUrl;
	private float userScore;
	
	// User
	private int result;
	private QnaDto dto;
	private List<UserDto> review;
	private int reviewTotalCount;
	private String content;
	private int count;
	
	
}