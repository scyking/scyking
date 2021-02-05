package com.scyking.common.intercepter;

import com.scyking.common.base.BaseController;
import com.scyking.common.base.UserInfo;
import com.scyking.common.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author scyking
 * @description
 * @createTime 2020/11/24 11:01
 **/
@Slf4j
@Component
public class CheckAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 鉴权已统一放到网关处理
        String userId = request.getHeader(Constants.HEADER_USER_ID);
        // 封装用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(Long.valueOf(userId));
        BaseController.setUserInfo(userInfo);
        return true;
    }
}
