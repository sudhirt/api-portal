package com.sudhirt.apiutils.portal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "INVALID_INPUT")
public class InvalidInputException extends RuntimeException {

    private static final long serialVersionUID = 7554020068463996833L;

    private List<String> description;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(String message, String error) {
        super();
        description = Arrays.asList(error);
    }

    public InvalidInputException(String message, List<String> errors) {
        super();
        this.description = errors;
    }

    public void addDetail(String detail) {
        if(description == null) {
            description = new ArrayList<>();
        }
        description.add(detail);
    }
}
