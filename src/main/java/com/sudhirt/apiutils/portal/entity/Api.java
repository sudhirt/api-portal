package com.sudhirt.apiutils.portal.entity;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Api extends AbstractAuditableEntity {

    private static final long serialVersionUID = -6220623925693419405L;

    @MapsId("id")
    @JoinColumns({
            @JoinColumn(name = "ResourceId", referencedColumnName = "id")
    })
    @ManyToOne
    public Resource resource;

    @EmbeddedId
    private ApiKey id;
    @Enumerated(EnumType.STRING)
    private ApiStatus status;
    @Column
    private Date releaseDate;
    @Column
    private Date endOfLife;
    @Column(columnDefinition = "TEXT")
    @Lob
    private String swaggerJson;

    public void setResource(Resource resource) {
        this.resource = resource;
        resource.addApi(this);
    }
}
