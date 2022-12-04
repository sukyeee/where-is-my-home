package com.mycom.myapp.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dao.LoginDao;
import com.mycom.myapp.user.dto.UserDto;

@Service
public class LoginServiceImple implements LoginService {


	@Autowired
	LoginDao loginDao;

	@Override
	public UserDto login(UserDto dto) {
		UserDto userDto = loginDao.login(dto.getUserEmail());
		// userDto 는 테이블에서 조회한 데이터가 포함
		// dto 는 client 가 전송한 데이터가 포함 
		
		if( userDto != null && userDto.getUserPassword().equals(dto.getUserPassword())) {
			System.out.println("222222왜갑자기안돼!!!!!!!!!!" + dto);
			
			return userDto;	
		}
		
		return null;
		
	}

	@Override
	public UserDto findPassword(String userEmail) {
		return loginDao.findPassword(userEmail);
	}
	
	

}
