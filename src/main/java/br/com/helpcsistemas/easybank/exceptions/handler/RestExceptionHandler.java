package br.com.helpcsistemas.easybank.exceptions.handler;

import br.com.helpcsistemas.easybank.exceptions.ExceptionResponse;
import br.com.helpcsistemas.easybank.exceptions.NotAcceptableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@Order(-1)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<ExceptionResponse> handlerNotAcceptableException(NotAcceptableException exception) {
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .message("Not Accept header")
                        .status(HttpStatus.NOT_ACCEPTABLE.value())
                        .build(), HttpStatus.NOT_ACCEPTABLE
        );
    }

}
