package com.sudhirt.apiutils.portal.config;

import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.helper.ApiDataHelper;
import com.sudhirt.apiutils.portal.service.ApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JpaConfigTest {

    @Autowired
    private ApiService apiService;

    @Test
    public void created_by_populated_for_saved_entities() {
        Api savedApi = apiService.save(ApiDataHelper.createApi(1)
                .iterator()
                .next());
        assertThat(savedApi).isNotNull();
    }
}
