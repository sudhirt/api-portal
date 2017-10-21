package com.sudhirt.apiutils.portal.service;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.exception.InvalidInputException;
import com.sudhirt.apiutils.portal.exception.NotFoundException;
import com.sudhirt.apiutils.portal.helper.ApiDataHelper;
import com.sudhirt.apiutils.portal.repository.ApiRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ApiServiceTest {

    @Autowired
    private ApiService service;
    @Autowired
    private ApiRepository repository;

    @Test
    public void save() {
        assertThat(repository.count()).isEqualTo(0);
        Api savedApi = service.save(ApiDataHelper.createApi(1)
                .iterator()
                .next());
        assertThat(savedApi.getId()).isNotNull();
        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    public void save_get_validate() {
        Api api = ApiDataHelper.createApi(1)
                .iterator()
                .next();
        Api savedApi = service.save(api);
        savedApi = service.get(savedApi.getId());
        assertThat(savedApi.getApplication()).isEqualTo(api.getApplication());
        assertThat(savedApi.getContact()).isEqualTo(api.getContact());
        assertThat(savedApi.getStatus()).isEqualTo(api.getStatus());
        assertThat(savedApi.getVersion()).isEqualTo(api.getVersion());
        assertThat(savedApi.getSwaggerJson()).isEqualTo(api.getSwaggerJson());
    }

    @Test
    public void get_non_existing() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> service.get("invalid_id"));
    }

    @Test
    public void link_valid_json() {
        Api savedApi = service.save(ApiDataHelper.createApi(1)
                .iterator()
                .next());
        assertThat(savedApi.getSwaggerJson()).isNull();
        service.update(savedApi.getId(), "{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\",\"key4\":\"value4\"}");
        savedApi = service.get(savedApi.getId());
        assertThat(savedApi.getSwaggerJson()).isNotNull();
    }

    @Test
    public void link_valid_json_to_non_existing_app() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> service.update("invalid_id", "{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\",\"key4\":\"value4\"}"));
    }

    @Test
    public void link_invalid_json() {
        Api savedApi = service.save(ApiDataHelper.createApi(1)
                .iterator()
                .next());
        assertThat(savedApi.getSwaggerJson()).isNull();
        assertThatExceptionOfType(InvalidInputException.class).isThrownBy(() -> service.update(savedApi.getId(), "{key1:\"value1\",\"key4\":\"value4\"}"));
    }

    @Test
    public void link_invalid_json_to_non_existing_app() {
        assertThatExceptionOfType(InvalidInputException.class).isThrownBy(() -> service.update("invalid_id", "{key1:\"value1\",\"key4\":\"value4\"}"));
    }
}
