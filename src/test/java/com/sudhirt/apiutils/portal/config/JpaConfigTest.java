package com.sudhirt.apiutils.portal.config;

import com.sudhirt.apiutils.portal.entity.Resource;
import com.sudhirt.apiutils.portal.helper.ApiDataHelper;
import com.sudhirt.apiutils.portal.service.ResourceService;
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
public class JpaConfigTest {

    @Autowired
    private ResourceService resourceService;

    @Test
    public void audit_fields_populated_for_saved_entities() {
        Resource savedResource = resourceService.save(ApiDataHelper.createResource(1)
                .iterator()
                .next());
        savedResource = resourceService.get(savedResource.getId());
        assertThat(savedResource).isNotNull();
        assertThat(savedResource.getCreatedDate()).isNotNull();
        assertThat(savedResource.getLastModifiedDate()).isNotNull();
    }
}
