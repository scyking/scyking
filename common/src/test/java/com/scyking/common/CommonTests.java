package com.scyking.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scyking.common.utils.JsonUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author scyking
 * @description
 **/
public class CommonTests {

    @Test
    public void jsonUtilsTest(){
        String jsonStr = "{\"name\":\"test\"}";
        try {
            Map map = JsonUtils.json2Map(jsonStr);
            System.out.println(map.get("name"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
