package com.sudhirt.apiutils.portal.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sudhirt.apiutils.portal.constant.ApiStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@JsonPropertyOrder({"id", "application", "contact", "version", "releaseDate", "endOfLife", "status"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceDTO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = -3751624559434980709L;

    @JsonProperty("id")
    private String identifier;
    @NotNull
    private String application;
    @NotNull
    @Email
    private String contact;
}
