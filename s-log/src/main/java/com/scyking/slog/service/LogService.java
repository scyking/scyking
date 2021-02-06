package com.scyking.slog.service;

import com.scyking.common.logs.ComLogEntity;
import com.scyking.slog.pojo.SysLog;
import com.scyking.slog.pojo.SysLogPageVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author scyking
 **/
public interface LogService {

    void insertLog(SysLog sysLog);

    void insertLog(ComLogEntity comLogEntity);

    void insertLogs(List<SysLog> sysLogs);

    List<SysLog> listLogs();

    List<SysLog> listLogs(SysLogPageVO vo);

    List<SysLog> listAllLogs(SysLogPageVO vo);

    long countLogs(SysLogPageVO vo);

    void download(HttpServletResponse response, SysLogPageVO sysLogVO);
}
