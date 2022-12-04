package com.mycom.myapp.notice.service;

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

import com.mycom.myapp.notice.dao.NoticeDao;
import com.mycom.myapp.notice.dto.NoticeDto;
import com.mycom.myapp.notice.dto.NoticeFileDto;
import com.mycom.myapp.notice.dto.NoticeParamDto;
import com.mycom.myapp.notice.dto.NoticeResultDto;
import com.mycom.myapp.user.dao.UserDao;
import com.mycom.myapp.user.dto.UserDto;


@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDao dao;
	
	@Autowired
	UserDao userDao;
	
	private final int SUCCESS = 1;
	private final int FAIL = -1;	

	@Value("${app.fileupload.uploadPath}")
	String uploadPath;

	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;
	
	
	@Override
	@Transactional
	public NoticeResultDto insert(NoticeDto notice, MultipartHttpServletRequest request) {
		NoticeResultDto resultDto = new NoticeResultDto();
		try {
			// DB에서 게시글 정보 가져옴
			int ret = dao.insert(notice);
			
			// 물리 파일 업로드 경로 설정
			File uploadDir = new File(uploadPath + File.separator + uploadFolder); // 업로드 용 폴더
			if(!uploadDir.exists()) uploadDir.mkdir();
			
			List<MultipartFile> fileList = request.getFiles("file");
			
			if(fileList != null && !fileList.isEmpty()) {
				for(MultipartFile partFile: fileList) { // 파일 하나씩 저장
					int noticeId = notice.getNoticeId();
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
					
					NoticeFileDto NoticeFileDto = new NoticeFileDto();
					NoticeFileDto.setNoticeId(noticeId);
					NoticeFileDto.setFileName(fileName);
					NoticeFileDto.setFileSize(partFile.getSize());
					NoticeFileDto.setFileContentType(partFile.getContentType());
					NoticeFileDto.setFileUrl(uploadFolder + "/" + savingFileName); // 이건 웹의 경로를 따라가기 때문에 /를 사용
				
					dao.insertFile(NoticeFileDto);
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
	public NoticeResultDto selectList(NoticeParamDto param) {
		NoticeResultDto resultDto = new NoticeResultDto();
		try {
			List<NoticeDto> list = dao.selectList(param);
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
	public NoticeResultDto selectListSearchWord(NoticeParamDto param) {
		NoticeResultDto resultDto = new NoticeResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<NoticeDto> list = dao.selectListSearchWord(param);
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
	public NoticeResultDto selectListSearchCode(NoticeParamDto param) {
		NoticeResultDto resultDto = new NoticeResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<NoticeDto> list = dao.selectListSearchCode(param);
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
	public NoticeResultDto selectListSearchWordAndCode(NoticeParamDto param) {
		NoticeResultDto resultDto = new NoticeResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<NoticeDto> list = dao.selectListSearchWordAndCode(param);
			int count = dao.selectListSearchWordAndCodeTotalCount(param);
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
	public NoticeResultDto selectDetail(NoticeParamDto param) {
		NoticeResultDto resultDto = new NoticeResultDto();
		try {
			// 현재 게시글을 현재 사용자가 읽었는지 확인
			int readCount = dao.selectUserReadCount(param);
			if(readCount == 0) { // 안 읽은 경우 
				dao.insertUserRead(param.getNoticeId(), param.getUserId());
				dao.updateNoticeReadCount(param.getNoticeId());
			}
			
			// DB에서 게시글 정보를 가져온다.
			NoticeDto dto = dao.selectDetail(param);
			// 게시글 작성자 이름을 가져온다.
			UserDto user = new UserDto();
			user.setUserId(dto.getUserId());
			String writerName = userDao.userDetail(user).getUserName();
			dto.setUserName(writerName);
			
			// 해당 게시글에 대한 첨부파일 정보를 추가
			List<NoticeFileDto> fileList = dao.selectDetailFileList(dto.getNoticeId());
			dto.setFileList(fileList);
			
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
	public NoticeResultDto delete(int noticeId) {
	    NoticeResultDto resultDto = new NoticeResultDto();
	    
	    try {
	        List<String> fileUrlList = dao.selectfileUrlDeleteList(noticeId);
	        for(String fileUrl : fileUrlList) {
	            File file = new File(uploadPath + File.separator, fileUrl);                
	            if(file.exists()) {
	                file.delete();
	            }
	        }
	        
	        // 삭제 순서
	        dao.deleteFile(noticeId);
	        dao.deleteUserRead(noticeId);
	        dao.delete(noticeId);        
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
	public NoticeResultDto update(NoticeDto notice, MultipartHttpServletRequest request) {
		NoticeResultDto resultDto = new NoticeResultDto();
		try {
			int ret = dao.update(notice); // 게시글을 수정
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder); // 업로드된 파일이 저장될 폴더 (디렉토리)
			if( !uploadDir.exists() ) uploadDir.mkdir();
	
			// 기존 물리적인 파일 삭제, 첨부 파일이 여러 개일 수 있는 것 감안
			List<String> fileUrlList = dao.selectfileUrlDeleteList(notice.getNoticeId());
			for(String fileUrl: fileUrlList) {
				File file = new File(uploadPath + File.separator + fileUrl); // fileUrl은 upload/ ... 생략
				if(file.exists()) {
					file.delete();
				}
			}
			
			dao.deleteFile(notice.getNoticeId());
			
			// 수정 과정에 새로 추가된 첨부 파일 추가
			List<MultipartFile> fileList = request.getFiles("file");
			if(fileList != null && !fileList.isEmpty()) {
				for(MultipartFile partFile : fileList) { // 파일 하나씩 저장
					
					int noticeId = notice.getNoticeId(); // 직전 등록된 게시글의 key값
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
					NoticeFileDto NoticeFileDto = new NoticeFileDto();
					NoticeFileDto.setNoticeId(noticeId);
					NoticeFileDto.setFileName(fileName);
					NoticeFileDto.setFileSize(partFile.getSize());
					NoticeFileDto.setFileContentType(partFile.getContentType());
					NoticeFileDto.setFileUrl(uploadFolder + "/" + savingFileName); // 이건 웹의 경로를 따라가기 때문에 /를 사용
					
					dao.insertFile(NoticeFileDto);
				}
			}
			
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

}
