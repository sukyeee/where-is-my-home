package com.mycom.myapp.code.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.mycom.myapp.code.dto.CodeDto;


@Mapper
public interface CodeDao {
	List<CodeDto> getCodeList(String groupCode);

}
