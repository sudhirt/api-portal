package com.sudhirt.apiutils.portal.helper;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import com.sudhirt.apiutils.portal.entity.Api;
import com.sudhirt.apiutils.portal.entity.ApiKey;
import com.sudhirt.apiutils.portal.entity.Resource;

import java.util.ArrayList;
import java.util.Collection;

public class ApiDataHelper {

    public static Collection<Resource> createResource(int count) {
        return createResource(count, "beta1");
    }

    public static Collection<Resource> createResource(int count, String version) {
        Collection<Resource> apis = new ArrayList<>();
        Resource api;
        for (int index = 1; index <= count; index++) {
            api = new Resource();
            api.setApplication("TEST_" + index);
            api.setContact("test\"+index+\"@test\"+index+\".com");
            apis.add(api);
        }
        return apis;
    }

    public static Collection<Api> createApi(Resource resource, int count) {
        return createApi(resource, count, ApiStatus.DEVELOPMENT);
    }

    public static Collection<Api> createApi(Resource resource, int count, ApiStatus status) {
        Collection<Api> apis = new ArrayList<>();
        Api api;
        for (int index = 1; index <= count; index++) {
            api = new Api();
            api.setStatus(status);
            api.setId(new ApiKey(resource.getId(), "v" + index));
            api.setResource(resource);
            apis.add(api);
        }
        return apis;
    }
}
