package com.sudhirt.apiutils.portal.resource;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
public class ApiResource implements Serializable {

    private static final long serialVersionUID = -3751624559434980709L;

    private String id;
    private String application;
    private String contact;
    private ApiStatus status;
    private Date releaseDate;
    private Date endOfLife;
    private String version;
    private String swaggerJson;
}
