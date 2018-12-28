package com.example.demo.controller;

import com.example.demo.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Api(value = "TestController", tags = {"测试控制器"})
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value = "测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "唯一标识", required = true, dataType = "String", paramType = "query")})
    @GetMapping("/{name}")
    public Object helloWorld(@PathVariable String name, @RequestParam("id") String id, @RequestBody String body, String none) {
        return name + id + body + none;
    }

    @GetMapping("/file/download")
    public void fileDownload(HttpServletResponse response) {
        // TODO 文件编码问题
        try {
            FileUtil.downloadFile(response, "D:\\tools\\mycat\\version.txt", System.currentTimeMillis() + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
