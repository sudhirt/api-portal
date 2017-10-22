package com.sudhirt.apiutils.portal.service;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.entity.ApiKey;
import com.sudhirt.apiutils.portal.exception.InvalidInputException;
import com.sudhirt.apiutils.portal.exception.NotFoundException;
import com.sudhirt.apiutils.portal.repository.ApiRepository;
import com.sudhirt.apiutils.portal.utils.JSONUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Service
public class ApiService {

    private final ApiRepository repository;

    public ApiService(ApiRepository repository) {
        this.repository = repository;
    }

    public Api get(String resourceId, String version) {
        Api api = repository.findOne(new ApiKey(resourceId, version));
        if (api != null) {
            return api;
        } else {
            throw new NotFoundException(resourceId);
        }
    }

    public List<Api> find(String resourceId) {
        List<Api> api = repository.findAllByIdResourceId(resourceId);
        if (!CollectionUtils.isEmpty(api)) {
            return api;
        } else {
            throw new NotFoundException(resourceId);
        }
    }

    public Api save(Api api) {
        return repository.save(api);
    }

    public void update(String resourceId, String version, String json) {
        try {
            JSONUtil.parseJSON(json);
            Api api = repository.findOne(new ApiKey(resourceId, version));
            if (api != null) {
                api.setSwaggerJson(json);
                repository.save(api);
            } else {
                throw new NotFoundException(resourceId + ":" + version);
            }
        } catch (IOException e) {
            InvalidInputException ex = new InvalidInputException();
            ex.addDetail("Invalid JSON provided");
            throw ex;
        }
    }
}
