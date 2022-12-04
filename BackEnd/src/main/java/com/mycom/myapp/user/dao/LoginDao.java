package com.mycom.myapp.user.dao;


import org.apache.ibatis.annotations.Mapper;

import com.mycom.myapp.user.dto.UserDto;

@Mapper
public interface LoginDao {
	public UserDto login(String userEmail);
	public UserDto findPassword(String userEmail);
}
