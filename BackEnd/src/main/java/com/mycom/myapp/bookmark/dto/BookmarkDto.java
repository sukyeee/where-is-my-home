package com.mycom.myapp.bookmark.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookmarkDto {	
	
	private int bookmarkId;
	private BigInteger houseDealId;
	private BigInteger houseId;

	private int userId;
	
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
	private String userName;
	private String build_year;
	private String dong;
	private String roadName;
	private String jibun;
	private String apartmentName;
    
}

