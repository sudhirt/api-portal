package com.sudhirt.apiutils.portal.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditableEntity implements Serializable {

    private static final long serialVersionUID = 5116283331220843942L;

    @Column(nullable = false)
    @CreatedDate
    private Date createdDate;
    @Column(nullable = false)
    @LastModifiedDate
    private Date lastModifiedDate;
}
