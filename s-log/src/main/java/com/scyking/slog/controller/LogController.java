package com.scyking.slog.controller;

import com.scyking.common.base.BaseResponse;
import com.scyking.slog.pojo.SysLog;
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
    BaseResponse<List<SysLog>> listSysLogs() {
        return BaseResponse.success().data(logService.listLogs());
    }

    @GetMapping("/download")
    void download(HttpServletResponse response) {
        logService.download(response);
    }

}
