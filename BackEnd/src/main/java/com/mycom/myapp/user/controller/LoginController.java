package com.mycom.myapp.user.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.service.LoginService;


@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	LoginService service;	
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;

	
	// User 정보 select : Get
	@PostMapping(value="/login")
	public ResponseEntity<Map<String, UserDto>> login(@RequestBody UserDto dto, HttpSession session, HttpServletRequest request){

		UserDto userDto = service.login(dto);
		
		Map<String, UserDto> map = new HashMap<>();
		if( userDto != null ) {
			// session 에 userDto 를 저장 
			session.setAttribute("userDto", userDto);
			System.out.println(userDto);
//			String userProfile = userDto.getUserProfileImageUrl();
//			Cookie cookie = new Cookie("userProfile", userProfile);
//			System.out.println("userProfile!:" + cookie.getValue());

			// client 에게 성공 결과를 json 으로 전달
			map.put("userDto", userDto);

			return new ResponseEntity<Map<String,UserDto>>(map, HttpStatus.OK);
		}
		
		return new ResponseEntity<Map<String,UserDto>>(map, HttpStatus.NOT_FOUND);
	}

	
		// User 정보 select : Get
		@GetMapping(value="/logout")
		public ResponseEntity<Map<String, Integer>> logout(HttpSession session){
			
			session.invalidate();
			
			Map<String, Integer> map = new HashMap<>();
			map.put("result", SUCCESS );
			
			return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
		}
	
		// User 정보 select : Get
		@GetMapping(value="/user/{userEmail}")
		public ResponseEntity<Map<String, UserDto>> findPassword(@PathVariable(value="userEmail") String userEmail){
			
			System.out.println("비밀번호 찾기 !!");
			UserDto userDto = service.findPassword(userEmail);

			Map<String, UserDto> map = new HashMap<>();
			
			if( userDto != null ) {
				map.put("UserDto", userDto);
				return new ResponseEntity<Map<String,UserDto>>(map, HttpStatus.OK);
			} else {
				return new ResponseEntity<Map<String,UserDto>>(map, HttpStatus.NOT_FOUND);
			}
			
		}
		
}