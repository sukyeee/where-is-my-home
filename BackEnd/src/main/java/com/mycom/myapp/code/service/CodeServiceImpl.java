package com.mycom.myapp.code.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myapp.code.dao.CodeDao;
import com.mycom.myapp.code.dto.CodeDto;
import com.mycom.myapp.code.dto.CodeResultDto;


@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	CodeDao dao;

	private final int SUCCESS = 1;
	private final int FAIL = -1;	

	@Override
	public CodeResultDto getCodeList(String groupCode) {
		CodeResultDto resultDto = new CodeResultDto();
		try {			
			List<CodeDto> list = dao.getCodeList(groupCode);
			resultDto.setList(list);
			resultDto.setResult(SUCCESS);
		} catch( Exception e ) {
			e.printStackTrace();
			resultDto.setResult(FAIL);
		}
		return resultDto;
	}

}
