package com.scyking.slog.pojo;

import cn.hutool.db.Page;
import com.scyking.common.logs.SystemLogModuleEnum;
import com.scyking.common.logs.SystemLogTypeEnum;

import java.time.LocalDateTime;

/**
 * @author scyking
 **/
public class SysLogPageVO extends Page {
    private static final long serialVersionUID = -2170900061348041829L;

    private SystemLogModuleEnum logType;

    private SystemLogTypeEnum optType;

    private LocalDateTime fromCreateTime;

    private LocalDateTime toCreateTime;

    @Override
    public void setPageNumber(int pageNumber) {
        // Page.pageNumber 0是第一页，修改为1是第一页
        super.setPageNumber(pageNumber - 1);
    }

    @Override
    public int getPageNumber() {
        // Page.pageNumber 0是第一页，修改为1是第一页
        return super.getPageNumber() + 1;
    }


    public SystemLogModuleEnum getLogType() {
        return logType;
    }

    public void setLogType(SystemLogModuleEnum logType) {
        this.logType = logType;
    }

    public SystemLogTypeEnum getOptType() {
        return optType;
    }

    public void setOptType(SystemLogTypeEnum optType) {
        this.optType = optType;
    }

    public LocalDateTime getFromCreateTime() {
        return fromCreateTime;
    }

    public void setFromCreateTime(LocalDateTime fromCreateTime) {
        this.fromCreateTime = fromCreateTime;
    }

    public LocalDateTime getToCreateTime() {
        return toCreateTime;
    }

    public void setToCreateTime(LocalDateTime toCreateTime) {
        this.toCreateTime = toCreateTime;
    }
}
