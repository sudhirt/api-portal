package com.sudhirt.apiutils.portal.service;

import com.sudhirt.apiutils.portal.entity.Resource;
import com.sudhirt.apiutils.portal.exception.InvalidInputException;
import com.sudhirt.apiutils.portal.exception.NotFoundException;
import com.sudhirt.apiutils.portal.repository.ResourceRepository;
import com.sudhirt.apiutils.portal.utils.JSONUtil;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class ResourceService {

    private final ResourceRepository repository;

    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }

    public Resource get(String id) {
        Optional<Resource> resourceHolder = repository.findOne(Example.of(new Resource().setId(id)));
        if (resourceHolder.isPresent()) {
            resourceHolder.get().getApis();
            return resourceHolder.get();
        } else {
            throw new NotFoundException(id);
        }
    }

    public Resource save(Resource resource) {
        return repository.save(resource);
    }
}
