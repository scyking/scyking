package com.scyking.slog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author scyking
 **/
@Data
@Document(collection = "sys_log")
public class SysLog<T> {
    @Id
    private String id;
    private String opt_id;                          //操作用户id
    private String opt_account;                     //操作人账号
    private String opt_name;                        //操作人name

    private T obj;                                  //操作主体
    private SystemLogModuleEnum log_type;           //日志类型

    private SystemLogTypeEnum opt_type;            //操作类型
    private String remark;                          //备注

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date create_at;             //记录时间
}
