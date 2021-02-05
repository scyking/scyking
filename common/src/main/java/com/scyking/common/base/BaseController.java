package com.scyking.common.base;

import org.springframework.util.Assert;

/**
 * 控制器基类
 *
 * @author scyking
 **/
public class BaseController {
    private static final ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public static void setUserInfo(UserInfo UserInfo) {
        threadLocal.set(UserInfo);
    }

    public Long getUserId() {
        UserInfo userInfo = threadLocal.get();
        Assert.notNull(userInfo, "未获取到用户信息");
        return userInfo.getUserId();
    }
}
