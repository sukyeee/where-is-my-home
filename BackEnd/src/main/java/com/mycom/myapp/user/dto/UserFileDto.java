package com.mycom.myapp.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserFileDto {
	private int fileId;
	private int userId;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private String fileUrl;
	private LocalDateTime registerDate;
	private String code;
}
