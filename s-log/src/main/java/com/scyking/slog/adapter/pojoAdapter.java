package com.scyking.slog.adapter;

import com.scyking.common.logs.ComLogEntity;
import com.scyking.slog.pojo.SysLog;
import org.springframework.beans.BeanUtils;

/**
 * java bean 适配器
 *
 * @author scyking
 **/
public class pojoAdapter {

    public static SysLog comLogEntity2SysLog(ComLogEntity comLogEntity) {
        SysLog sysLog = new SysLog();
        BeanUtils.copyProperties(comLogEntity, sysLog);
        return sysLog;
    }

}
