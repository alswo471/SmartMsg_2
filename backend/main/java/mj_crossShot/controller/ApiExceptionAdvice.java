package mj_crossShot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import mj_crossShot.common.ErrorResponse;
import mj_crossShot.common.error.BadRequestException;
import mj_crossShot.common.error.MemberNotFoundException;
import mj_crossShot.common.error.duplMemIdException;


@RestControllerAdvice("mj_crossShot.controller")
public class ApiExceptionAdvice {

	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoData() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("존재하는 유저가 없습니다."));
		
	}
	
	@ExceptionHandler(duplMemIdException.class)
	public ResponseEntity<ErrorResponse> handleDuplData() {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("중복된 아이디 입니다."));
		
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> BadRequestData() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("필수 입니다. 내용을 입력해주세요."));
		
	}
}
