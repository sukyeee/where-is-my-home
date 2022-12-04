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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.review.dto.ReviewParamDto;
import com.mycom.myapp.review.dto.ReviewResultDto;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.service.UserService;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	UserService userService;
	

	private final int SUCCESS = 1;
	private final int FAIL = -1;

	// HttpStatus Code 로 처리 결과를 Wrapping 하기 위해 ResponseEntity를 사용 
	
	// registerForm -> 공통 코드 select : GET
//	@GetMapping(value="/register")
//	public ResponseEntity<List<CodeDto>> registerForm(CodeDto dto, HttpServletRequest request){
//	
//		List<CodeDto> codeList = codeDao.getCodeList("001"); // 회원구분 : 001
//		request.setAttribute("codeList", codeList);
//		
//		System.out.println("UserServlet - registerForm  codeList" + codeList);
//		
//		Map<String, List<CodeDto>> map = new HashMap<>();
//	
//		if( codeList != null ) {
//			map.put("codeList", codeList);
//			return new ResponseEntity<List<CodeDto>>(codeList, HttpStatus.OK);
//		} else {
//			map.put("codeList", codeList);
//			return new ResponseEntity<List<CodeDto>>(codeList, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//	}
	
	// register -> insert : POST
	@PostMapping(value="/register")
	public ResponseEntity<Map<String, Integer>> register(@RequestBody UserDto dto){
		int ret = userService.register(dto);
		Map<String, Integer> map = new HashMap<>();

		if( ret == SUCCESS ) {
			map.put("result", SUCCESS);
			return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
		} else {
			map.put("result", FAIL);
			return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
		}
	}

	// userDetail
//	@GetMapping(value="/user")
//	public ResponseEntity<UserDto> userDetail(UserDto dto, HttpServletRequest request){
//		 HttpSession session = request.getSession();
//		 dto = (UserDto) session.getAttribute("userDto");
//		 
//		 System.out.println(dto);
//			if( dto != null ) {
//				session.setAttribute("userDto", dto);
//
//				return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
//			}
//			
//			return new ResponseEntity<UserDto>(dto, HttpStatus.NOT_FOUND);
//	}
	
	// userDelete
	@DeleteMapping(value="/user/{userId}")
	public ResponseEntity<Map<String, Integer>> userDelete(@PathVariable(value="userId") int userId, HttpServletRequest request){
		
		int ret = userService.delete(userId);

		Map<String, Integer> map = new HashMap<>();
		
		if( ret == SUCCESS ) {
			map.put("result", SUCCESS);
			return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
		} else {
			map.put("result", FAIL);
			return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
		}
		
	}
	

	// userUpdate
	@PostMapping(value="/user/{userId}")
	public ResponseEntity<Map<String, Integer>> userUpdate(UserDto dto, HttpServletRequest request){
			
		int ret = userService.update(dto);

		Map<String, Integer> map = new HashMap<>();
		
		if( ret == SUCCESS ) {
			map.put("result", SUCCESS);
			return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
		} else {
			map.put("result", FAIL);
			return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
		}
		
	}
	
	// 내가 쓴 리뷰
	@GetMapping(value="/user/reviews")
	public ResponseEntity<UserResultDto> userReview(UserDto dto, HttpServletRequest request){
		 HttpSession session = request.getSession();
		 UserDto userDto = (UserDto) session.getAttribute("userDto");
		 dto.setUserId(userDto.getUserId());
		 System.out.println(dto);
		 
		 UserResultDto resultDto = userService.userReview(dto);
						
		if( resultDto != null ) {
			return new ResponseEntity<UserResultDto>(resultDto, HttpStatus.OK);
		}
		
		return new ResponseEntity<UserResultDto>(resultDto, HttpStatus.NOT_FOUND);
	}
	
	// 프로필 업데이트

	// userProfileUpdate
	@PostMapping(value="/user/profile")
	public ResponseEntity<UserResultDto> userProfileUpdate(UserDto dto, MultipartHttpServletRequest request){
			
		 HttpSession session = request.getSession();
		 UserDto userDto = (UserDto) session.getAttribute("userDto");
		 dto.setUserId(userDto.getUserId());
		 
		UserResultDto resultDto = userService.userProfileUpdate(dto, request);

		if( resultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(resultDto, HttpStatus.OK);
		}
		
	}
	
}