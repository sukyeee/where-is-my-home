package com.mycom.myapp.notice.controller;

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
import com.mycom.myapp.notice.dto.NoticeDto;
import com.mycom.myapp.notice.dto.NoticeParamDto;
import com.mycom.myapp.notice.dto.NoticeResultDto;
import com.mycom.myapp.notice.service.NoticeService;
import com.mycom.myapp.user.dto.UserDto;

@RestController
@CrossOrigin(
		origins="http://localhost:5500",
		allowCredentials="true",
		allowedHeaders="*",
		methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
				RequestMethod.OPTIONS, RequestMethod.HEAD}
)
@RequestMapping("/notices")
public class NoticeController{
	
	@Autowired
	NoticeService service;
	
	@Autowired
	CodeService codeService;

	private final int SUCCESS = 1;
	private final int FAIL = -1;
	private final String NOTICE_GROUP_CODE = "002";
	
	@GetMapping("/codes")
	private ResponseEntity<CodeResultDto> selectCodeList(){
		CodeResultDto resultDto = codeService.getCodeList(NOTICE_GROUP_CODE);
		return new ResponseEntity<>(resultDto, HttpStatus.OK);
	}
	
	@GetMapping("")
	// searchWord, category 유무에 따라 분리해서 처리
	private ResponseEntity<NoticeResultDto> selectList(NoticeParamDto param){
		NoticeResultDto resultDto;
		
		if(!param.getSearchWord().isEmpty() && !param.getCode().isEmpty()) {			
			resultDto = service.selectListSearchWordAndCode(param);
		} else if(!param.getSearchWord().isEmpty() ) {
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
	
	@GetMapping("/{noticeId}")
	private ResponseEntity<NoticeResultDto> selectDetail(@PathVariable int noticeId, HttpSession session){
		NoticeParamDto param = new NoticeParamDto();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		if(userDto == null) {			
			NoticeResultDto resultDto = new NoticeResultDto();
			return new ResponseEntity<NoticeResultDto>(resultDto, HttpStatus.FORBIDDEN);			
		}
		param.setUserId(userDto.getUserId()); // 현재 login 되어서 상세 요청을 한 사용자 정보
		param.setNoticeId(noticeId);
		
		NoticeResultDto resultDto = service.selectDetail(param);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PostMapping("")
	private ResponseEntity<NoticeResultDto> insert(NoticeDto dto, MultipartHttpServletRequest request){
		UserDto userDto = (UserDto) request.getSession().getAttribute("userDto");
		if(userDto == null) {			
			NoticeResultDto resultDto = new NoticeResultDto();
			return new ResponseEntity<NoticeResultDto>(resultDto, HttpStatus.FORBIDDEN);			
		}
		dto.setUserId(userDto.getUserId());
		NoticeResultDto resultDto = service.insert(dto, request); // request를 전달해서 첨부된 파일을 service에서 처리
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PostMapping("/{noticeId}")
	private ResponseEntity<NoticeResultDto> update(NoticeDto dto, MultipartHttpServletRequest request){
		NoticeResultDto resultDto = service.update(dto, request);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping("/{noticeId}")
	private ResponseEntity<NoticeResultDto> delete(@PathVariable int noticeId){
		NoticeResultDto resultDto = service.delete(noticeId);
		
		if(resultDto.getResult() == SUCCESS) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
}