package com.scyking.sgateway.client;

import com.scyking.common.base.BaseResponse;

/**
 * 描述：
 *
 * @author zhangdongli
 * @version 1.0
 * @since 2020/11/2/11:06
 */

public class UserAuthClientFallback implements UserAuthClient {

    @Override
    public BaseResponse<?> checkUserToken(String auth) {
        return null;
    }
}
