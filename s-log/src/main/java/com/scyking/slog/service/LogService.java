package com.scyking.slog.service;

import com.scyking.slog.pojo.SysLog;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author scyking
 **/
public interface LogService {

    void insertLog(SysLog sysLog);

    void insertLogs(List<SysLog> sysLogs);

    List<SysLog> listLogs();

    void download(HttpServletResponse response);
}
