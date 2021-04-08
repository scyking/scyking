package com.scyking.ucenter.pojo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author scyking
 **/
@Data
public class Mail {

    private String id;
    private String from;
    private String to;
    private String subject;
    private String text;
    private LocalDateTime sentDate;
    private String cc;
    private String bcc;
    private String status;
    private String error;

    @JsonIgnore
    private MultipartFile[] multipartFiles;
}
