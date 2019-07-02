package de.jonashackt.springbootvuejs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DienstNotFoundException extends RuntimeException {
    public DienstNotFoundException() {
        super();
    }

    public DienstNotFoundException(String message) {
        super(message);
    }

    public DienstNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
