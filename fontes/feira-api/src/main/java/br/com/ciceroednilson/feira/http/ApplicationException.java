package br.com.ciceroednilson.feira.http;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {

    private HttpStatus httpStatus;

    public ApplicationException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ApplicationException(final String message, final HttpStatus httpStatus, final Exception ex) {
        super(message, ex);
        this.httpStatus = httpStatus;
    }
}
