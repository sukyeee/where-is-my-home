package com.mycom.myapp.house.service;

import com.mycom.myapp.house.dto.NewsResultDto;

public interface SearchNewsService {

	public NewsResultDto crawlingNews(String sido, String dong);
}
