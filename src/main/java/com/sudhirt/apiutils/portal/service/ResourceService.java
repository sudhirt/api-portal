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
        Resource api = repository.findOne(id);
        if (api != null) {
            return api;
        } else {
            throw new NotFoundException(id);
        }
    }

    public Resource save(Resource api) {
        return repository.save(api);
    }

    public void update(String id, String json) {
        try {
            JSONUtil.parseJSON(json);
            Resource api = repository.findOne(id);
            if (api != null) {
                api.setSwaggerJson(json);
                repository.save(api);
            } else {
                throw new NotFoundException(id);
            }
        } catch(IOException e) {
            InvalidInputException ex = new InvalidInputException();
            ex.addDetail("Invalid JSON provided");
            throw ex;
        }
    }
}
