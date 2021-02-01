package com.scyking.common.logs;

import lombok.Getter;

/**
 * @author scyking
 **/
@Getter
public enum SystemLogTypeEnum {

    /**
     * 审核相关
     */
    ACCEPT_LOG("通过"),
    REJECT_LOG("驳回"),
    /**
     * CRUD相关
     */
    QUERY_LOG("查询"),
    SAVE_LOG("保存"),
    UPDATE_LOG("更新"),
    DELETE_LOG("删除"),
    /**
     * 登录相关
     */
    REGISTER_LOG("注册"),
    LOGIN_LOG("登录"),

    /**
     * 依靠主体
     */
    OTHER("其他");

    private String name;

    SystemLogTypeEnum(String name) {
        this.name = name;
    }
}