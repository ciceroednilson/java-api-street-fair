package br.com.ciceroednilson.feira.http;

import br.com.ciceroednilson.feira.domain.HttpDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class HandleException {

    private static final String LOG_API_FAIR = "API-FAIR - Exception ";

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<HttpDomain> applicationExceptionHandle(final ApplicationException exception) {
        final HttpStatus httpStatus = Objects.isNull(exception.getHttpStatus()) ? HttpStatus.INTERNAL_SERVER_ERROR : exception.getHttpStatus();
        log.error(LOG_API_FAIR, exception);
        final HttpDomain httpDomain =
                HttpDomain.builder()
                        .httpCode(httpStatus.value())
                        .httpMessage(httpStatus.getReasonPhrase())
                        .message(exception.getMessage())
                        .build();
        return new ResponseEntity(httpDomain, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpDomain> exceptionHandle(final Exception exception) {
        log.error(LOG_API_FAIR, exception);
        final HttpDomain httpDomain =
                HttpDomain.builder()
                        .httpCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .httpMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .message(exception.getMessage())
                        .build();
        return new ResponseEntity(httpDomain, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<HttpDomain>> methodArgumentNotValidExceptionHandle(final MethodArgumentNotValidException exception) {
        log.error(LOG_API_FAIR, exception);
        final List<HttpDomain> list = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            list.add(HttpDomain.builder()
                    .httpCode(HttpStatus.BAD_REQUEST.value())
                    .httpMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(fieldName.concat(errorMessage))
                    .build());
        });
        return new ResponseEntity(list, HttpStatus.BAD_REQUEST);
    }
}
