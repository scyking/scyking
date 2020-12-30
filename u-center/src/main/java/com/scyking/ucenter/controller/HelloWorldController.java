package com.scyking.ucenter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Tag(name = "测试接口", description = "测试")
@RestController
@RequestMapping("test")
public class HelloWorldController {

    @Operation(summary = "hello world")
    @RequestMapping(value = "/hello-world", method = RequestMethod.GET)
    public String helloWorld() {
        return "hello world !";
    }

    @Operation(summary = "下载测试")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) {
        String content = "测试";
        String filename = "测试.txt";

        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");

        try {
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(filename.getBytes(), "ISO-8859-1")
            );
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(content.getBytes());
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
