package com.sudhirt.apiutils.portal.entity;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
public class Resource extends AbstractAuditableEntity {

    private static final long serialVersionUID = -7282713304997015713L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String contact;
    @OneToMany(mappedBy = "resource")
    private Collection<Api> apis;

    public void addApi(Api api) {
        if(apis == null) {
            apis = new ArrayList<>();
        }
        apis.add(api);
    }
}
