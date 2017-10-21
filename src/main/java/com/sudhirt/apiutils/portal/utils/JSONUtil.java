package com.sudhirt.apiutils.portal.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class JSONUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void parseJSON(String json) throws IOException {
        objectMapper.readTree(json);
    }
}
