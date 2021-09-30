package de.workshops.bookdemo.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ControllerAdvice
public class BookExceptionHandler {

	@ExceptionHandler(BookException.class)
	public ResponseEntity<ErrorDTO> error(BookException ex) {
		ErrorDTO dto = ErrorDTO.builder().errorCode(ex.getErrorCode()).build();
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(dto);
	}
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	static class ErrorDTO {
		private String errorCode;
		private String errorParam;
		
	}
}
