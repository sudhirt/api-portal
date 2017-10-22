package com.sudhirt.apiutils.portal.mapper;

import com.sudhirt.apiutils.portal.entity.Resource;
import com.sudhirt.apiutils.portal.resource.ResourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ResourceMapper {

    @Mapping(target = "identifier", source = "id")
    public abstract ResourceDTO toResource(Resource api);

    @Mapping(target = "id", ignore = true)
    public abstract Resource toEntity(ResourceDTO apiResource);
}
