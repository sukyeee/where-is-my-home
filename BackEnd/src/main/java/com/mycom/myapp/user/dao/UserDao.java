package com.mycom.myapp.user.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myapp.review.dto.HouseReviewDto;
import com.mycom.myapp.review.dto.ReviewParamDto;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserFileDto;

@Mapper
public interface UserDao {
	public int userRegister(UserDto userdto);
	public int userUpdate(UserDto userDto);
	public int userDelete(int userId);
	public UserDto userDetail(UserDto userDto);
	// 매물 리뷰 평균 점수 업데이트
	public int userScoreUpdate(@Param("userId") int userId, @Param("avgScore") float avgFloat);
	// 내가 쓴 리뷰 리스트
	public List<UserDto> userReview(UserDto dto);
	// 내가 쓴 리뷰 리스트 개수
	public int userReviewTotalCount(UserDto dto);
	// 프로필 이미지 변경
	public int userProfileUpdate(UserDto dto);
	public List<String> selectfileUrlDeleteList(int userId);
	public void deleteFile(int userId);
	public void insertFile(UserFileDto userFileDto);
	
}
