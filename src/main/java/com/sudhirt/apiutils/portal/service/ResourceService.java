package com.sudhirt.apiutils.portal.service;

import com.sudhirt.apiutils.portal.entity.Resource;
import com.sudhirt.apiutils.portal.exception.InvalidInputException;
import com.sudhirt.apiutils.portal.exception.NotFoundException;
import com.sudhirt.apiutils.portal.repository.ResourceRepository;
import com.sudhirt.apiutils.portal.utils.JSONUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ResourceService {

    private final ResourceRepository repository;

    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }

    public Resource get(String id) {
        Resource resource = repository.findOne(id);
        if (resource != null) {
            resource.getApis();
            return resource;
        } else {
            throw new NotFoundException(id);
        }
    }

    public Resource save(Resource resource) {
        return repository.save(resource);
    }
}
