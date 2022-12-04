package com.mycom.myapp.event.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myapp.event.dao.EventDao;
import com.mycom.myapp.event.dto.EventDto;
import com.mycom.myapp.event.dto.EventFileDto;
import com.mycom.myapp.event.dto.EventParamDto;
import com.mycom.myapp.event.dto.EventResultDto;


@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventDao dao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;	

	@Value("${app.fileupload.uploadPath}")
	String uploadPath;

	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;
	
	
	@Override
	@Transactional
	public EventResultDto insert(EventDto event, MultipartHttpServletRequest request) {
		EventResultDto resultDto = new EventResultDto();
		try {
			// DB에서 게시글 정보 가져옴
			int ret = dao.insert(event);
			
			// 물리 파일 업로드 경로 설정
			File uploadDir = new File(uploadPath + File.separator + uploadFolder); // 업로드 용 폴더
			if(!uploadDir.exists()) uploadDir.mkdir();
			
			List<MultipartFile> fileList = request.getFiles("file");
			
			if(fileList != null && !fileList.isEmpty()) {
				for(MultipartFile partFile: fileList) { // 파일 하나씩 저장
					int eventId = event.getEventId();
					String fileName = partFile.getOriginalFilename();
					
					// Random UUID File id
					UUID uuid = UUID.randomUUID();
					
					// 파일 확장자
					String extension = FilenameUtils.getExtension(fileName); // 원래 파일의 확장자만 추출
					
					// 실제 저장할 파일 전체 이름은
					String savingFileName = uuid + "." + extension;
					
					File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
					
					// 파일 객체를 통해서 파일을 저장
					partFile.transferTo(destFile);		
					
					EventFileDto eventFileDto = new EventFileDto();
					eventFileDto.setEventId(eventId);
					eventFileDto.setFileName(fileName);
					eventFileDto.setFileSize(partFile.getSize());
					eventFileDto.setFileContentType(partFile.getContentType());
					eventFileDto.setFileUrl(uploadFolder + "/" + savingFileName); // 이건 웹의 경로를 따라가기 때문에 /를 사용
				
					dao.insertFile(eventFileDto);
				}
			}
			
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
			throw new RuntimeException();
		}
		return resultDto;
	}

	@Override
	public EventResultDto selectList(EventParamDto param) {
		EventResultDto resultDto = new EventResultDto();
		try {
			List<EventDto> list = dao.selectList(param);
			// 목록 조회 시 보여줄 썸네일 이미지를 넣어줌
			for(EventDto dto : list) {
				// 해당 게시글에 대한 첨부파일 정보를 추가
				List<EventFileDto> fileList = dao.selectDetailFileList(dto.getEventId());
				// 첫번째 파일이 이미지인 경우, 첫번째 파일로 설정
				if(!fileList.isEmpty() && fileList.get(0).getFileContentType().startsWith("image")) { 
					dto.setThumbnailFile(fileList.get(0));
				} else { // 대체 이미지로 설정 -> 프론트에서 보여줌
					dto.setThumbnailFile(null);
				}
				// 유저가 이벤트를 신청했는지 여부
				int isJoined = dao.selectUserJoin(dto.getEventId(), param.getUserId());
				System.out.println(isJoined);
				dto.setJoined(isJoined == 0 ? false : true);
			}
			
			int count = dao.selectListTotalCount();
			resultDto.setList(list);
			resultDto.setCount(count);
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public EventResultDto selectListSearchWord(EventParamDto param) {
		EventResultDto resultDto = new EventResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<EventDto> list = dao.selectListSearchWord(param);
			// 목록 조회 시 보여줄 썸네일 이미지를 넣어줌
			for(EventDto dto : list) {
				// 해당 게시글에 대한 첨부파일 정보를 추가
				List<EventFileDto> fileList = dao.selectDetailFileList(dto.getEventId());
				// 첫번째 파일이 이미지인 경우, 첫번째 파일로 설정
				if(!fileList.isEmpty() && fileList.get(0).getFileContentType().startsWith("image")) { 
					dto.setThumbnailFile(fileList.get(0));
				} else { // 대체 이미지로 설정 -> 프론트에서 보여줌
					dto.setThumbnailFile(null);
				}
				// 유저가 이벤트를 신청했는지 여부
				int isJoined = dao.selectUserJoin(dto.getEventId(), param.getUserId());
				dto.setJoined(isJoined == 0 ? false : true);
			}
			int count = dao.selectListSearchWordTotalCount(param);
			resultDto.setList(list);
			resultDto.setCount(count);
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public EventResultDto selectListSearchCode(EventParamDto param) {
		EventResultDto resultDto = new EventResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<EventDto> list = dao.selectListSearchCode(param);
			// 목록 조회 시 보여줄 썸네일 이미지를 넣어줌
			for(EventDto dto : list) {
				// 해당 게시글에 대한 첨부파일 정보를 추가
				List<EventFileDto> fileList = dao.selectDetailFileList(dto.getEventId());
				// 첫번째 파일이 이미지인 경우, 첫번째 파일로 설정
				if(!fileList.isEmpty() && fileList.get(0).getFileContentType().startsWith("image")) { 
					dto.setThumbnailFile(fileList.get(0));
				} else { // 대체 이미지로 설정 -> 프론트에서 보여줌
					dto.setThumbnailFile(null);
				}
				// 유저가 이벤트를 신청했는지 여부
				int isJoined = dao.selectUserJoin(dto.getEventId(), param.getUserId());
				dto.setJoined(isJoined == 0 ? false : true);
			}
			int count = dao.selectListSearchCodeTotalCount(param);
			resultDto.setList(list);
			resultDto.setCount(count);
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public EventResultDto selectDetail(EventParamDto param) {
		EventResultDto resultDto = new EventResultDto();
		try {
			// 현재 게시글을 현재 사용자가 읽었는지 확인
			int readCount = dao.selectUserReadCount(param);
			if(readCount == 0) { // 안 읽은 경우 
				dao.insertUserRead(param.getEventId(), param.getUserId());
				dao.updateEventReadCount(param.getEventId());
			}
			
			// DB에서 게시글 정보를 가져온다.
			EventDto dto = dao.selectDetail(param);
			
			// 해당 게시글에 대한 첨부파일 정보를 추가
			List<EventFileDto> fileList = dao.selectDetailFileList(param.getEventId());
			dto.setFileList(fileList);

			// 유저가 이벤트를 신청했는지 여부
			int isJoined = dao.selectUserJoin(param.getEventId(), param.getUserId());
			dto.setJoined(isJoined == 0 ? false : true);
			System.out.println("joined: " + isJoined);
			
			
			// boardResultDto의 일부인 boardDto 를 설정
			resultDto.setDto(dto);
			
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	@Transactional
	public EventResultDto delete(int eventId) {
	    EventResultDto resultDto = new EventResultDto();
	    
	    try {
	        List<String> fileUrlList = dao.selectfileUrlDeleteList(eventId);
	        for(String fileUrl : fileUrlList) {
	            File file = new File(uploadPath + File.separator, fileUrl);                
	            if(file.exists()) {
	                file.delete();
	            }
	        }
	        
	        // 삭제 순서
	        dao.deleteFile(eventId);
	        dao.deleteUserRead(eventId);
	        dao.delete(eventId);        
	        resultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        resultDto.setResult(FAIL);
	        // 파일업로드 등이 실패할 경우 IOException 이 발생하는데, IOException 을 throw 할 경우 Rollback 되지 않는다.
	        // UncheckedException 발생
	        throw new RuntimeException();            
	    }
	    
	    return resultDto;
	}

	@Override
	@Transactional
	public EventResultDto update(EventDto event, MultipartHttpServletRequest request) {
		EventResultDto resultDto = new EventResultDto();
		try {
			int ret = dao.update(event); // 게시글을 수정
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder); // 업로드된 파일이 저장될 폴더 (디렉토리)
			if( !uploadDir.exists() ) uploadDir.mkdir();
	
			// 기존 물리적인 파일 삭제, 첨부 파일이 여러 개일 수 있는 것 감안
			List<String> fileUrlList = dao.selectfileUrlDeleteList(event.getEventId());
			for(String fileUrl: fileUrlList) {
				File file = new File(uploadPath + File.separator + fileUrl); // fileUrl은 upload/ ... 생략
				if(file.exists()) {
					file.delete();
				}
			}
			
			dao.deleteFile(event.getEventId());
			
			// 수정 과정에 새로 추가된 첨부 파일 추가
			List<MultipartFile> fileList = request.getFiles("file");
			
			if(fileList != null && !fileList.isEmpty()) {
				for(MultipartFile partFile : fileList) { // 파일 하나씩 저장
					
					int eventId = event.getEventId(); // 직전 등록된 게시글의 key값
					String fileName = partFile.getOriginalFilename(); // 첨부된 원래 파일 명, 이 이름으로는 바로 저장하지 않고 UUID 를 이용해서 중복 불가한 파일이름을 만든다.
					
					// Random UUID File id
					UUID uuid = UUID.randomUUID();
					
					// 파일 확장자
					String extension = FilenameUtils.getExtension(fileName); // 원래 파일의 확장자만 추출
					
					// 실제 저장할 파일 전체 이름은
					String savingFileName = uuid + "." + extension;
					
					File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
					
					// 파일 객체를 통해서 파일을 저장
					partFile.transferTo(destFile);		
					
					// 테이블에 첨부파일 정보 저장
					EventFileDto eventFileDto = new EventFileDto();
					eventFileDto.setEventId(eventId);
					eventFileDto.setFileName(fileName);
					eventFileDto.setFileSize(partFile.getSize());
					eventFileDto.setFileContentType(partFile.getContentType());
					eventFileDto.setFileUrl(uploadFolder + "/" + savingFileName); // 이건 웹의 경로를 따라가기 때문에 /를 사용
					
					dao.insertFile(eventFileDto);
				}
			}
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	@Transactional
	public EventResultDto insertUserJoin(EventParamDto param) { // 이벤트 참여 신청
		EventResultDto resultDto = new EventResultDto();
		try {
			dao.insertUserJoin(param);
			resultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	@Transactional
	public EventResultDto deleteUserJoinByUserIdAndEventId(EventParamDto param) { // 이벤트 참여 취소
		EventResultDto resultDto = new EventResultDto();
		try {
			dao.deleteUserJoinByUserIdAndEventId(param);
			resultDto.setResult(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

}
