package com.scyking.slog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scyking.common.logs.SystemLogModuleEnum;
import com.scyking.common.logs.SystemLogTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author scyking
 **/
@Data
@Document(collection = "sys_log")
public class SysLog<T> {
    @Id
    private String id;
    private String optId;                          //操作用户id
    private String optAccount;                     //操作人账号
    private String optName;                        //操作人name

    private T obj;                                  //操作主体
    private SystemLogModuleEnum logType;           //日志类型

    private SystemLogTypeEnum optType;            //操作类型
    private String remark;                          //备注

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private LocalDateTime createTime;                    //记录时间
}
