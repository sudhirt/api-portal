package com.sudhirt.apiutils.portal.resource;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class ApiDTO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = -884077147978245982L;

    private String resourceId;
    private ApiStatus status;
    private Date releaseDate;
    private Date endOfLife;
    @NotNull
    private String version;
}
