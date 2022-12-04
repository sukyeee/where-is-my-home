 package com.mycom.myapp.bookmark.controller;


 import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.bookmark.dto.BookmarkDto;
import com.mycom.myapp.bookmark.dto.BookmarkParamDto;
import com.mycom.myapp.bookmark.dto.BookmarkResultDto;
import com.mycom.myapp.bookmark.service.BookmarkService;
import com.mycom.myapp.user.dto.UserDto;



@RestController
@CrossOrigin(
		origins="http://localhost:5500",
		allowCredentials="true",
		allowedHeaders="*",
		methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
				RequestMethod.OPTIONS, RequestMethod.HEAD}
)
@RequestMapping("/bookmarks")
 public class BookmarkController {

 @Autowired
 BookmarkService service;

 private final int SUCCESS = 1;
 private final int FAIL = -1;


 	@GetMapping("")
	private ResponseEntity<BookmarkResultDto> selectList(BookmarkParamDto param, HttpSession session){
		BookmarkResultDto resultDto = new BookmarkResultDto();

		System.out.println("bookmarklist ????? : " + param);
		
		// param에 유저 정보 같이 넣어줘야함. (자신이 좋아요 한 북마크만 보여주기)
		UserDto userDto = (UserDto) session.getAttribute("userDto");
//		System.out.println("여기도나오나?????????????????????????????????");
		
		param.setUserId(userDto.getUserId());
		System.out.println(param);

		if(param.getDong() != null) {
			resultDto = service.selectListFilterDong(param);
		} 
		else {
			resultDto = service.selectList(param);	
		}
		
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
 	
 	
	@GetMapping("/{bookmarkId}")
	private ResponseEntity<BookmarkResultDto> selectDetail(@PathVariable int bookmarkId, HttpSession session){
		
		BookmarkParamDto param = new BookmarkParamDto();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		System.out.println("userdtoooooooooooo" + userDto);
		param.setUserId(userDto.getUserId());
		param.setBookmarkId(bookmarkId); // PathVariable 로 넘어온 게시글 key
		
		BookmarkResultDto resultDto = service.selectDetail(param);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	 @PostMapping("")
	 private ResponseEntity<BookmarkResultDto> bookmarkInsert(@RequestBody BookmarkDto bookmarkDto, HttpSession session) {
	
		 bookmarkDto.setUserId( ((UserDto) session.getAttribute("userDto")).getUserId());
//		 bookmarkDto.setUserId(bookmarkDto.getUserId());
		 BookmarkResultDto bookmarkResultDto = service.insert(bookmarkDto);
		 System.out.println("insert!!" + bookmarkDto);

		 if (bookmarkResultDto.getResult() == SUCCESS) {
			 return new ResponseEntity<BookmarkResultDto>(bookmarkResultDto, HttpStatus.OK);
		 } else {
			 return new ResponseEntity<BookmarkResultDto>(bookmarkResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		
	 }
	 
	 
		@DeleteMapping("/{bookmarkId}")
		private ResponseEntity<BookmarkResultDto> delete(@PathVariable int bookmarkId){
			BookmarkResultDto resultDto = service.delete(bookmarkId);
			
			if(resultDto.getResult() == SUCCESS) {
				return new ResponseEntity<>(resultDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
			}
		}
		
	 

 	}