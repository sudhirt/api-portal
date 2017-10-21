package com.sudhirt.apiutils.portal.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ApiError implements Serializable {

    private static final long serialVersionUID = 7554020068463996833L;

    private String error;
    private List<String> description;

    public ApiError(String error) {
        super();
        this.error = error;
    }

    public ApiError(String message, String error) {
        super();
        description = Arrays.asList(error);
    }

    public ApiError(String message, List<String> errors) {
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
