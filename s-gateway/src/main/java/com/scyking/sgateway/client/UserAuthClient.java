package com.scyking.sgateway.client;

import com.scyking.common.base.HttpResult;
import com.scyking.common.base.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述：用户鉴权客户端，调用统一用户认证系统的用户鉴权接口，完成认证
 *
 * @author zhangdongli
 * @version 1.0
 * @since 2020/11/2/10:28
 */

@FeignClient(name = "u-center", fallback = UserAuthClientFallback.class)
public interface UserAuthClient {
    @RequestMapping(value = "/interface/user/info", method = RequestMethod.GET)
    HttpResult<UserInfo> checkUserToken(@RequestHeader("Authorization") String auth);
}
