package com.sudhirt.apiutils.portal.entity;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Data
public class Resource implements Serializable {

    private static final long serialVersionUID = -7282713304997015713L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String application;
    @Column(nullable = false)
    private String contact;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApiStatus status;
    @Column
    private Date releaseDate;
    @Column
    private Date endOfLife;
    @Column(nullable = false)
    private String version;
    @Column(columnDefinition = "TEXT")
    @Lob
    private String swaggerJson;
    @CreatedDate
    private Date createdDate;
}
