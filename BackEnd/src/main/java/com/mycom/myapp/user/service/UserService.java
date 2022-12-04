package com.mycom.myapp.user.service;


import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.review.dto.ReviewParamDto;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;


public interface UserService {
	public int register(UserDto userDto);
	public int update(UserDto userDto);
	public int delete(int userId);
	public UserDto detail(UserDto userDto);
	
	public UserResultDto userReview(UserDto dto);
	public UserResultDto userProfileUpdate(UserDto dto, MultipartHttpServletRequest request);
}
