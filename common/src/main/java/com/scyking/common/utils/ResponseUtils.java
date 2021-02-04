package com.scyking.common.utils;

import com.scyking.common.base.BaseResponse;

/**
 * @author scyking
 **/
public class ResponseUtils {

    /**
     * 是否成功获取响应结果数据
     *
     * @param response 响应结果
     * @return
     */
    public static boolean hasData(BaseResponse response) {
        return isSuccess(response) && response.getData() != null;
    }

    public static boolean isSuccess(BaseResponse response) {
        return !isFailure(response);
    }

    public static boolean isFailure(BaseResponse response) {
        return response == null || response.getCode() != BaseResponse.SUCCESS;
    }
}
