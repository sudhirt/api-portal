package com.sudhirt.apiutils.portal.service;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.entity.ApiKey;
import com.sudhirt.apiutils.portal.exception.InvalidInputException;
import com.sudhirt.apiutils.portal.exception.NotFoundException;
import com.sudhirt.apiutils.portal.repository.ApiRepository;
import com.sudhirt.apiutils.portal.utils.JSONUtil;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    private final ApiRepository repository;

    public ApiService(ApiRepository repository) {
        this.repository = repository;
    }

    public Api get(String resourceId, String version) {
        Optional<Api> apiHolder = repository.findOne(Example.of(new Api().setId(new ApiKey(resourceId, version))));
        if (apiHolder.isPresent()) {
            return apiHolder.get();
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
            Optional<Api> apiHolder = repository.findOne(Example.of(new Api().setId(new ApiKey(resourceId, version))));
            if (apiHolder.isPresent()) {
                apiHolder.get().setSwaggerJson(json);
                repository.save(apiHolder.get());
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
