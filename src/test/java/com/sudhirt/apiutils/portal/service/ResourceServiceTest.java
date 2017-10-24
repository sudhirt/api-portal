package com.sudhirt.apiutils.portal.service;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.entity.Resource;
import com.sudhirt.apiutils.portal.exception.InvalidInputException;
import com.sudhirt.apiutils.portal.exception.NotFoundException;
import com.sudhirt.apiutils.portal.helper.ApiDataHelper;
import com.sudhirt.apiutils.portal.repository.ApiRepository;
import com.sudhirt.apiutils.portal.repository.ResourceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ResourceServiceTest {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private ApiRepository apiRepository;

    @Test
    public void save() {
        assertThat(resourceRepository.count()).isEqualTo(0);
        Resource savedResource = resourceService.save(ApiDataHelper.createResource(1)
                .iterator()
                .next());
        assertThat(savedResource.getId()).isNotNull();
        assertThat(resourceRepository.count()).isEqualTo(1);
    }

    @Test
    public void save_get_validate() {
        Resource resource = ApiDataHelper.createResource(1)
                .iterator()
                .next();
        Resource savedResource = resourceService.save(resource);
        savedResource = resourceService.get(savedResource.getId());
        assertThat(savedResource.getName()).isEqualTo(resource.getName());
        assertThat(savedResource.getContact()).isEqualTo(resource.getContact());
    }

    @Test
    public void get_non_existing() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> resourceService.get("invalid_id"));
    }

    @Test
    public void add_api() {
        Resource resource = ApiDataHelper.createResource(1)
                .iterator()
                .next();
        Resource savedResource = resourceService.save(resource);
        Api api = ApiDataHelper.createApi(savedResource, 1)
                .iterator()
                .next();
        assertThat(apiRepository.count()).isEqualTo(0);
        Api savedApi = apiService.save(api);
        assertThat(apiRepository.count()).isEqualTo(1);
    }

    @Test
    public void add_multiple_api() {
        Resource resource = ApiDataHelper.createResource(1)
                .iterator()
                .next();
        Resource savedResource = resourceService.save(resource);
        Iterator<Api> apiIterator = ApiDataHelper.createApi(savedResource, 3)
                .iterator();
        assertThat(apiRepository.count()).isEqualTo(0);
        apiService.save(apiIterator.next());
        apiService.save(apiIterator.next());
        apiRepository.findAll();
        apiService.save(apiIterator.next());
        assertThat(apiRepository.count()).isEqualTo(3);

        savedResource = resourceService.get(savedResource.getId());
        assertThat(savedResource.getApis()).hasSize(3);
    }

    @Test
    public void link_valid_json() {
        Resource savedResource = resourceService.save(ApiDataHelper.createResource(1)
                .iterator()
                .next());
        Api savedApi = apiService.save(ApiDataHelper.createApi(savedResource, 1)
                .iterator()
                .next());
        apiService.update(savedResource.getId(), savedApi.getId()
                .getVersion(), "{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\",\"key4\":\"value4\"}");
        savedApi = apiService.get(savedApi.getId()
                .getResourceId(), savedApi.getId()
                .getVersion());
        assertThat(savedApi.getSwaggerJson()).isNotNull();
    }

    @Test
    public void link_valid_json_to_invalid_resource() {
        Resource savedResource = resourceService.save(ApiDataHelper.createResource(1)
                .iterator()
                .next());
        Api savedApi = apiService.save(ApiDataHelper.createApi(savedResource, 1)
                .iterator()
                .next());
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> apiService.update("invalid_resource_id", savedApi.getId()
                .getVersion(), "{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\",\"key4\":\"value4\"}"));
    }

    @Test
    public void link_valid_json_to_invalid_api_version() {
        Resource savedResource = resourceService.save(ApiDataHelper.createResource(1)
                .iterator()
                .next());
        Api savedApi = apiService.save(ApiDataHelper.createApi(savedResource, 1)
                .iterator()
                .next());
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> apiService.update(savedResource.getId(), "invalid_version", "{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\",\"key4\":\"value4\"}"));
    }

    @Test
    public void link_invalid_json() {
        Resource savedResource = resourceService.save(ApiDataHelper.createResource(1)
                .iterator()
                .next());
        Api savedApi = apiService.save(ApiDataHelper.createApi(savedResource, 1)
                .iterator()
                .next());
        assertThatExceptionOfType(InvalidInputException.class).isThrownBy(() -> apiService.update(savedResource.getId(), savedApi.getId()
                .getVersion(), "{key1:\"value1\",\"key4\":\"value4\"}"));
    }

    @Test
    public void link_invalid_json_to_invalid_resource() {
        Resource savedResource = resourceService.save(ApiDataHelper.createResource(1)
                .iterator()
                .next());
        Api savedApi = apiService.save(ApiDataHelper.createApi(savedResource, 1)
                .iterator()
                .next());
        assertThatExceptionOfType(InvalidInputException.class).isThrownBy(() -> apiService.update("invalid_resource", savedApi.getId()
                .getVersion(), "{key1:\"value1\",\"key4\":\"value4\"}"));
    }

    @Test
    public void link_invalid_json_to_invalid_api_version() {
        Resource savedResource = resourceService.save(ApiDataHelper.createResource(1)
                .iterator()
                .next());
        Api savedApi = apiService.save(ApiDataHelper.createApi(savedResource, 1)
                .iterator()
                .next());
        assertThatExceptionOfType(InvalidInputException.class).isThrownBy(() -> apiService.update(savedResource.getId(), "invalid_version", "{key1:\"value1\",\"key4\":\"value4\"}"));
    }
}
