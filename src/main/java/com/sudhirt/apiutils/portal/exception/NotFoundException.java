package com.sudhirt.apiutils.portal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1738302335597194514L;

    private String id;

    public NotFoundException(String id) {
        super();
        this.id = id;
    }
}
