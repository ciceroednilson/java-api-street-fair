package br.com.ciceroednilson.feira.http;

import br.com.ciceroednilson.feira.domain.HttpDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class HandleExceptionTest {

    @InjectMocks
    private HandleException handleException;

    @Test
    public void applicationExceptionHandle() {
        final ResponseEntity<HttpDomain> responseEntity =
                handleException.applicationExceptionHandle(
                        new ApplicationException(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST)
                );
        assertTrue(responseEntity.getBody().getHttpMessage().contains(HttpStatus.BAD_REQUEST.getReasonPhrase()));
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void applicationExceptionHandleNullHttpStatus() {
        final ResponseEntity<HttpDomain> responseEntity =
                handleException.applicationExceptionHandle(
                        new ApplicationException(HttpStatus.BAD_REQUEST.getReasonPhrase(), null)
                );
        assertTrue(responseEntity.getBody().getHttpMessage().contains(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void exceptionHandle() {
        final ResponseEntity<HttpDomain> responseEntity =
                handleException.exceptionHandle(
                        new Exception(HttpStatus.BAD_REQUEST.getReasonPhrase())
                );
        assertTrue(responseEntity.getBody().getHttpMessage().contains(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
