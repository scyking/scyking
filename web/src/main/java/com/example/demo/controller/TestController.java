package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
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
    public Object helloWorld(@PathVariable String name, @RequestParam("id") String id) {
        return name + id;
    }

    @GetMapping("/file/download")
    public void fileDownload(HttpServletResponse response) {
        String filename = "三网手机实名制认证.pdf";
        String filePath = "E:\\file\\人资相关\\手机三要素认证";
        File file = new File(filePath + "/" + filename);
        if (file.exists()) { //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            response.setContentType("application/octet-stream;charset=UTF-8");

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
