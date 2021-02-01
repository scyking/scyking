package com.scyking.slog.service.impl;

import com.scyking.slog.pojo.SysLog;
import com.scyking.slog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author scyking
 **/
@Service
public class LogServiceImpl implements LogService {

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
}
