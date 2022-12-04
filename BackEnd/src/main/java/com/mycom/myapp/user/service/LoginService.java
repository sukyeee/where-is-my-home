package com.mycom.myapp.user.service;


import com.mycom.myapp.user.dto.UserDto;


public interface LoginService {
	public UserDto login(UserDto dto);
	public UserDto findPassword(String userEmail);

}
