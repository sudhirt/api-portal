package com.sudhirt.apiutils.portal.helper;

import com.sudhirt.apiutils.portal.constant.ApiStatus;
import com.sudhirt.apiutils.portal.entity.Api;

import java.util.ArrayList;
import java.util.Collection;

public class ApiDataHelper {

    public static Collection<Api> createApi(int count) {
        return createApi(count, ApiStatus.INACTIVE, "beta1");
    }

    public static Collection<Api> createApi(int count, ApiStatus status, String version) {
        Collection<Api> apis = new ArrayList<>();
        Api api;
        for(int index=1; index <= count; index++) {
            api = new Api();
            api.setApplication("TEST_" + index);
            api.setContact("test\"+index+\"@test\"+index+\".com");
            api.setStatus(status);
            api.setVersion(version);
            apis.add(api);
        }
        return apis;
    }
}
