package com.mycom.myapp.bookmark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.myapp.bookmark.dao.BookmarkDao;
import com.mycom.myapp.bookmark.dto.BookmarkDto;
import com.mycom.myapp.bookmark.dto.BookmarkParamDto;
import com.mycom.myapp.bookmark.dto.BookmarkResultDto;

	
@Service
	public class BookmarkServiceImpl implements BookmarkService {
	
	@Autowired
	BookmarkDao dao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;


	@Override
	public BookmarkResultDto selectList(BookmarkParamDto param) {

		
		BookmarkResultDto resultDto = new BookmarkResultDto();
		
		try {
			List<BookmarkDto> list = dao.selectList(param);
			int count = dao.selectListTotalCount(param);
			resultDto.setList(list);
			resultDto.setCount(count);
			
			List<String> dongList = dao.selectDongList(param);
			resultDto.setDongList(dongList);
			
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	@Override
	public BookmarkResultDto selectListFilterDong(BookmarkParamDto param) {
		BookmarkResultDto resultDto = new BookmarkResultDto();
		try {
			// 목록, 총건수를 가져온다.
			List<BookmarkDto> list = dao.selectListFilterDong(param);
			int count = dao.selectListFilterDongTotalCount(param);
			resultDto.setList(list);
			resultDto.setCount(count);
			
			List<String> dongList = dao.selectDongList(param);
			resultDto.setDongList(dongList);
			
			resultDto.setResult(SUCCESS);
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

	
	@Override
	public BookmarkResultDto selectDetail(BookmarkParamDto param) {
		BookmarkResultDto resultDto = new BookmarkResultDto();
		try {

			BookmarkDto dto = dao.selectDetail(param);

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
	public BookmarkResultDto insert(BookmarkDto dto) {
	BookmarkResultDto BookmarkResultDto = new BookmarkResultDto();
		
		try {
			
			dao.insert(dto); // useGeneratedKeys="true" keyProperty="BookmarkId"
			BookmarkResultDto.setResult(SUCCESS);

		}catch(Exception e) {
			e.printStackTrace();
			BookmarkResultDto.setResult(FAIL);
		}
		return BookmarkResultDto;
	}

	@Override
	public BookmarkResultDto delete(int bookmarkId) {
		BookmarkResultDto resultDto = new BookmarkResultDto();
	    
	    try {
	        dao.delete(bookmarkId);        
	        resultDto.setResult(SUCCESS);
	        
	    }catch(Exception e) {
	        e.printStackTrace();
	        resultDto.setResult(FAIL);
	    }
	    
	    return resultDto;
	}


}