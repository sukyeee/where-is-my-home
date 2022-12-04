// package com.mycom.myapp.config;
//
//
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
//
// import com.mycom.myboard.dto.BoardResultDto;
//
//
//// 개별 controller 에서 처리되지 않은 예외에 대한 글로벌한 예외 처리를 진행한다.
// @ControllerAdvice
// public class GlobalExceptionHandler {
//
// // 모든 예외를 전부 처리
// @ExceptionHandler(Exception.class)
// public ResponseEntity<BoardResultDto> handleErrorResponseEntity(Exception e) {
// /*
// * 에러 jsp 에 대한 분기 처리가 아닌
// * 주된 요청이 비동기이기 때문에 json 으로 결과를 리턴하는 형식으로 작성한다.
// *
// * */
//
// e.printStackTrace();
//
// System.out.println("GlobalExceptionHandler !!");
//
// BoardResultDto boardResultDto = new BoardResultDto();
// boardResultDto.setResult(-1); // 성공: 1, 실패: -1
//
// return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
// }
// }
