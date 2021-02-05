package com.scyking.slog.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.scyking.common.utils.ExcelUtils;
import com.scyking.common.utils.Excels;
import com.scyking.common.utils.JsonUtils;
import com.scyking.slog.pojo.SysLog;
import com.scyking.slog.pojo.SysLogPageVO;
import com.scyking.slog.service.LogService;
import com.scyking.slog.util.QueryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author scyking
 **/
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    QueryUtils queryUtils;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void insertLog(SysLog sysLog) {
        mongoTemplate.insert(sysLog);
    }

    @Override
    public void insertLogs(List<SysLog> sysLogs) {
        mongoTemplate.insertAll(sysLogs);
    }

    @Override
    public List<SysLog> listLogs() {
        return mongoTemplate.find(new Query(), SysLog.class);
    }

    @Override
    public List<SysLog> listLogs(SysLogPageVO vo) {
        return mongoTemplate.find(queryUtils.buildPage(vo), SysLog.class);
    }

    @Override
    public List<SysLog> listAllLogs(SysLogPageVO vo) {
        return mongoTemplate.find(queryUtils.build(vo), SysLog.class);
    }

    @Override
    public long countLogs(SysLogPageVO vo) {
        return mongoTemplate.count(queryUtils.build(vo), SysLog.class);
    }

    @Override
    public void download(HttpServletResponse response, SysLogPageVO sysLogVO) {
        List<SysLog> logs = listAllLogs(sysLogVO);
        String fileName = "操作日志.xlsx";
        Excels excels = new Excels()
                .workbook(fileName)
                .row("日志类型", "操作主体", "操作类型", "操作人账号", "记录时间", "备注");
        int i = 1;
        for (SysLog log : logs) {
            excels.row(i, log.getLogType().toString(),
                    JsonUtils.object2Json(log.getObj()),
                    log.getOptType().toString(),
                    log.getOptAccount(),
                    DateUtil.format(log.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN),
                    log.getRemark());
            i++;
        }
        ExcelUtils.export(fileName, excels, response);
    }
}
