package com.scyking.slog.controller;

import cn.hutool.db.PageResult;
import com.scyking.common.base.HttpResult;
import com.scyking.slog.pojo.SysLog;
import com.scyking.slog.pojo.SysLogPageVO;
import com.scyking.slog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author scyking
 **/
@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping
    HttpResult<?> listSysLogs(SysLogPageVO sysLogVO) {
        List<SysLog> logs = logService.listLogs(sysLogVO);
        long total = logService.countLogs(sysLogVO);
        PageResult<SysLog> pageResult = new PageResult<>(sysLogVO.getPageNumber(), sysLogVO.getPageSize(), Long.valueOf(total).intValue());
        pageResult.addAll(logs);
        return HttpResult.success().data(pageResult);
    }

    @GetMapping("/download")
    void download(HttpServletResponse response, SysLogPageVO sysLogVO) {
        logService.download(response, sysLogVO);
    }

}
