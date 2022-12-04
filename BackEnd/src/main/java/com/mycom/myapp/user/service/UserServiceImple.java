package com.mycom.myapp.user.service;


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

import com.mycom.myapp.user.dao.UserDao;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserFileDto;
import com.mycom.myapp.user.dto.UserResultDto;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	UserDao userDao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Value("${app.fileupload.uploadPath}")
	String uploadPath;

	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;
	
	
	@Override
	public int register(UserDto userDto) {
		return userDao.userRegister(userDto);
	}


	@Override
	public int update(UserDto userDto) {
		return userDao.userUpdate(userDto);
	}


	@Override
	public int delete(int userId) {
		return userDao.userDelete(userId);
	}


	@Override
	public UserDto detail(UserDto userDto) {
		return userDao.userDetail(userDto);
	}


	@Override
	public UserResultDto userReview(UserDto dto) {
		
		
		UserResultDto resultDto = new UserResultDto();
		
		try {
			List<UserDto> list = userDao.userReview(dto);
			int count = userDao.userReviewTotalCount(dto);
			
			resultDto.setReview(list);
			resultDto.setReviewTotalCount(count);
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
		
	}


	@Override
	@Transactional
	public UserResultDto userProfileUpdate(UserDto dto, MultipartHttpServletRequest request) {
	
		System.out.println("userProfile...service");
		UserResultDto resultDto = new UserResultDto();
		try {
			
			File uploadDir = new File(uploadPath + File.separator + "img"); // 업로드된 파일이 저장될 폴더 (디렉토리)
			if( !uploadDir.exists() ) uploadDir.mkdir();
	
			// 기존 물리적인 파일 삭제, 첨부 파일이 여러 개일 수 있는 것 감안
			List<String> fileUrlList = userDao.selectfileUrlDeleteList(dto.getUserId());
			for(String fileUrl: fileUrlList) {
				File file = new File(uploadPath + File.separator + fileUrl); // fileUrl은 upload/ ... 생략
				if(file.exists()) {
					file.delete();
				}
			}
			
			userDao.deleteFile(dto.getUserId());
			
//			 수정 과정에 새로 추가된 첨부 파일 추가
			List<MultipartFile> fileList = request.getFiles("file");
			if(fileList != null && !fileList.isEmpty()) {
				for(MultipartFile partFile : fileList) { // 파일 하나씩 저장
					
					int userId = dto.getUserId(); // 직전 등록된 게시글의 key값
					String fileName = partFile.getOriginalFilename(); // 첨부된 원래 파일 명, 이 이름으로는 바로 저장하지 않고 UUID 를 이용해서 중복 불가한 파일이름을 만든다.
					
					// Random UUID File id
					UUID uuid = UUID.randomUUID();
					
					// 파일 확장자
					String extension = FilenameUtils.getExtension(fileName); // 원래 파일의 확장자만 추출
					
					// 실제 저장할 파일 전체 이름은
					String savingFileName = uuid + "." + extension;
					
					File destFile = new File(uploadPath + File.separator + "img" + File.separator + savingFileName);
					
					// 파일 객체를 통해서 파일을 저장
					partFile.transferTo(destFile);		
					
					// 테이블에 첨부파일 정보 저장
					UserFileDto userFileDto = new UserFileDto();
					userFileDto.setUserId(userId);
					userFileDto.setFileName(fileName);
					userFileDto.setFileSize(partFile.getSize());
					userFileDto.setFileContentType(partFile.getContentType());
					userFileDto.setFileUrl("img" + "/" + savingFileName); // 이건 웹의 경로를 따라가기 때문에 /를 사용
					
					userDao.insertFile(userFileDto);
					
					dto.setUserProfileImageUrl("img" + "/" + savingFileName);
					userDao.userProfileUpdate(dto);
					
					resultDto.setUserProfileImageUrl("img" + "/" + savingFileName);

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
