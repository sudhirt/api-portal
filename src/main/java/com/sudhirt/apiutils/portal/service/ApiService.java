package com.sudhirt.apiutils.portal.service;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.exception.InvalidInputException;
import com.sudhirt.apiutils.portal.exception.NotFoundException;
import com.sudhirt.apiutils.portal.repository.ApiRepository;
import com.sudhirt.apiutils.portal.utils.JSONUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ApiService {

    private final ApiRepository repository;

    public ApiService(ApiRepository repository) {
        this.repository = repository;
    }

    public Api get(String id) {
        Api api = repository.findOne(id);
        if (api != null) {
            return api;
        } else {
            throw new NotFoundException(id);
        }
    }

    public Api save(Api api) {
        return repository.save(api);
    }

    public void update(String id, String json) {
        try {
            JSONUtil.parseJSON(json);
            Api api = repository.findOne(id);
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
