package com.scyking.common.logs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 通用日志实体
 *
 * @author scyking
 **/
@Data
public class ComLogEntity<T> {

    private String id;
    private String optId;                          //操作用户id
    private String optAccount;                     //操作人账号

    private T obj;                                  //操作主体
    private SystemLogModuleEnum logType;           //日志类型

    private SystemLogTypeEnum optType;            //操作类型
    private String remark;                          //备注

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private LocalDateTime createTime;                    //记录时间
}
