package com.sudhirt.apiutils.portal.service;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.helper.ApiDataHelper;
import com.sudhirt.apiutils.portal.repository.ApiRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

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
        Api savedApi = service.save(ApiDataHelper.createApi(1).iterator().next());
        assertThat(savedApi.getId()).isNotNull();
        assertThat(repository.count()).isEqualTo(1);
    }
}
