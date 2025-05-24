package br.com.vitor.geradorsenha.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GeradorException extends Exception {

    private final HttpStatus status;

    public GeradorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
