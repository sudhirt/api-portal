package com.sudhirt.apiutils.portal.mapper;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.resource.ApiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ApiMapper {

    @Mapping(target = "resourceId", source = "id.resourceId")
    @Mapping(target = "version", source = "id.version")
    public abstract ApiDTO toResource(Api api);

    public abstract List<ApiDTO> toResource(List<Api> apis);

    @Mapping(target = "status", source = "status", defaultValue = "DEVELOPMENT")
    @Mapping(target = "id.resourceId", source = "resourceId")
    @Mapping(target = "id.version", source = "version")
    public abstract Api toEntity(ApiDTO apiDto);
}
