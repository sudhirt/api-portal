package com.sudhirt.apiutils.portal.mapper;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.resource.ApiResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ApiMapper {

    @Mapping(target = "identifier", source = "id")
    public abstract ApiResource toResource(Api api);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status", defaultValue = "INACTIVE")
    public abstract Api toEntity(ApiResource apiResource);
}
