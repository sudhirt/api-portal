package com.sudhirt.apiutils.portal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKey implements Serializable {

    private static final long serialVersionUID = -6670371272277017559L;

    private String resourceId;
    private String version;
}
