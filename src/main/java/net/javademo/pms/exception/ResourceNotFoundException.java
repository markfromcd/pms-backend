package net.javademo.pms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{//when no that data, exception and throw notfound
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
