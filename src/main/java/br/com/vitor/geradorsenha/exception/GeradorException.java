package br.com.vitor.geradorsenha.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GeradorException extends Exception{

    HttpStatus statusCode;

    public GeradorException(String message, HttpStatus statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
