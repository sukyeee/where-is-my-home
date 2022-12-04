package com.mycom.myapp.event.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.code.dto.CodeDto;
import com.mycom.myapp.code.dto.CodeResultDto;
import com.mycom.myapp.code.service.CodeService;
import com.mycom.myapp.event.dto.EventDto;
import com.mycom.myapp.event.dto.EventParamDto;
import com.mycom.myapp.event.dto.EventResultDto;
import com.mycom.myapp.event.service.EventService;
import com.mycom.myapp.user.dto.UserDto;

@RestController
@CrossOrigin(
		origins= {"http://127.0.0.1:5500","http://localhost:5500"},
		allowCredentials="true",
		allowedHeaders="*",
		methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
				RequestMethod.OPTIONS, RequestMethod.HEAD}
)
@RequestMapping("/events")
public class EventController{
	
	@Autowired
	EventService service;
	
	@Autowired
	CodeService codeService;

	private final int SUCCESS = 1;
	private final int FAIL = -1;
	private final String EVENT_GROUP_CODE = "003";
	
	@GetMapping("/codes")
	private ResponseEntity<CodeResultDto> selectCodeList(){
		CodeResultDto resultDto = codeService.getCodeList(EVENT_GROUP_CODE);
		return new ResponseEntity<>(resultDto, HttpStatus.OK);
	}
	
	@GetMapping("")
	// searchWord, category 유무에 따라 분리해서 처리
	private ResponseEntity<EventResultDto> selectList(EventParamDto param, HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		if(userDto == null) {			
			EventResultDto resultDto = new EventResultDto();
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.FORBIDDEN);			
		}
		param.setUserId(userDto.getUserId()); // 현재 login 되어서 상세 요청을 한 사용자 정보
		
		EventResultDto resultDto;
		
		if(!param.getSearchWord().isEmpty() ) {
			resultDto = service.selectListSearchWord(param);
		} else if(!param.getCode().isEmpty()){
			resultDto = service.selectListSearchCode(param);
		} else {
			resultDto = service.selectList(param);			
		}
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@GetMapping("/{eventId}")
	private ResponseEntity<EventResultDto> selectDetail(@PathVariable int eventId, HttpSession session){
		EventParamDto param = new EventParamDto();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		if(userDto == null) {			
			EventResultDto resultDto = new EventResultDto();
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.FORBIDDEN);			
		}
		System.out.println(userDto.toString());
		param.setEventId(eventId); // PathVariable 로 넘어온 게시글 key
		param.setUserId(userDto.getUserId()); // 현재 login 되어서 상세 요청을 한 사용자 정보
		
		EventResultDto resultDto = service.selectDetail(param);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PostMapping("")
	private ResponseEntity<EventResultDto> insert(EventDto dto, MultipartHttpServletRequest request){
		UserDto userDto = (UserDto) request.getSession().getAttribute("userDto");
		if(userDto == null) {			
			EventResultDto resultDto = new EventResultDto();
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.FORBIDDEN);			
		}
		dto.setUserId(userDto.getUserId());
		EventResultDto resultDto = service.insert(dto, request); // request를 전달해서 첨부된 파일을 service에서 처리
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PostMapping("/{eventId}")
	private ResponseEntity<EventResultDto> update(EventDto dto, MultipartHttpServletRequest request){
		EventResultDto resultDto = service.update(dto, request);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping("/{eventId}")
	private ResponseEntity<EventResultDto> delete(@PathVariable int eventId){
		EventResultDto resultDto = service.delete(eventId);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PostMapping("/join/{eventId}")
	private ResponseEntity<EventResultDto> joinEvent(EventParamDto param, HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		if(userDto == null) {			
			EventResultDto resultDto = new EventResultDto();
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.FORBIDDEN);			
		}
		param.setUserId(userDto.getUserId());
		EventResultDto resultDto = service.insertUserJoin(param);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}

	@DeleteMapping("/join/{eventId}")
	private ResponseEntity<EventResultDto> cancelEvent(@PathVariable int eventId, HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		if(userDto == null) {			
			EventResultDto resultDto = new EventResultDto();
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.FORBIDDEN);			
		}
		EventParamDto param = new EventParamDto();
		param.setUserId(userDto.getUserId());
		param.setEventId(eventId);
		EventResultDto resultDto = service.deleteUserJoinByUserIdAndEventId(param);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
}