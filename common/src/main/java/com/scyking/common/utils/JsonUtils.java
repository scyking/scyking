package com.scyking.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scyking.common.base.JsonException;

import java.util.Map;

/**
 * json工具类
 * <p>
 *     封装jackson处理json字符串
 * </p>
 *
 * @author scyking
 **/
public class JsonUtils {

    /**
     * json字符串转map
     *
     * @param jsonStr 待转换字符串
     * @return LinkedHashMap
     * @throws JsonProcessingException
     */
    public static Map json2Map(String jsonStr){
        return json2Object(jsonStr, Map.class);
    }

    public static <T> T json2Object(String jsonStr, Class<T> valueType){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (JsonProcessingException e) {
            throw new JsonException("json 类型转换异常",e);
        }
    }

    public static String object2Json(Object object){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonException("json 类型转换异常",e);
        }
    }
}
