package com.mycom.myapp.user.dto;

import java.util.Date;

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

public class UserDto {
	private int userId;
	private String userName;
	private String userNickname;
	private String userPassword;
	private String userEmail;
	private Date userRegisterDate;
	private String code;
	private String userProfileImageUrl;
	private float userScore;
	private String content;
	private String apartmentName;
	private int score;
	private int reviewId;

	private boolean isEditOpen;

	public void setUserProfileImageUrl(String userProfileImageUrl) {
		if( userProfileImageUrl == null || "null".equals(userProfileImageUrl) || "".equals(userProfileImageUrl)) {
			this.userProfileImageUrl = "/img/noProfile.png";
		}else {
			this.userProfileImageUrl = userProfileImageUrl;
		}
	}
	
}