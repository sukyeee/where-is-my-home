package com.mycom.myapp.code.service;


import java.util.List;

import com.mycom.myapp.code.dto.CodeDto;
import com.mycom.myapp.code.dto.CodeResultDto;


public interface CodeService {
	CodeResultDto getCodeList(String groupCode);

}
