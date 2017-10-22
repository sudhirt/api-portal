package com.sudhirt.apiutils.portal.helper;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import com.sudhirt.apiutils.portal.entity.Resource;

import java.util.ArrayList;
import java.util.Collection;

public class ApiDataHelper {

    public static Collection<Resource> createApi(int count) {
        return createApi(count, ApiStatus.DEVELOPMENT, "beta1");
    }

    public static Collection<Resource> createApi(int count, ApiStatus status, String version) {
        Collection<Resource> apis = new ArrayList<>();
        Resource api;
        for(int index=1; index <= count; index++) {
            api = new Resource();
            api.setApplication("TEST_" + index);
            api.setContact("test\"+index+\"@test\"+index+\".com");
            api.setStatus(status);
            api.setVersion(version);
            apis.add(api);
        }
        return apis;
    }
}
