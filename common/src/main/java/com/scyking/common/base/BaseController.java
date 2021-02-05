package com.scyking.common.base;

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
}
