package com.scyking.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @author scyking
 * @description 封装jackson处理json字符串
 **/
public class JsonUtils {

    /**
     * json字符串转map
     *
     * @param jsonStr 待转换字符串
     * @return LinkedHashMap
     * @throws JsonProcessingException
     */
    public static Map json2Map(String jsonStr) throws JsonProcessingException {
        return json2Object(jsonStr, Map.class);
    }

    public static <T> T json2Object(String jsonStr, Class<T> valueType) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, valueType);
    }

    public static String object2Json(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
