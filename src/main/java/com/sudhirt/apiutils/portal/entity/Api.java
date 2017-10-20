package com.sudhirt.apiutils.portal.entity;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Data
public class Api implements Serializable {

    private static final long serialVersionUID = -7282713304997015713L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(columnDefinition = "clob", nullable = false)
    @Lob
    private String swaggerJson;
    @CreatedDate
    private Date createdDate;
}
