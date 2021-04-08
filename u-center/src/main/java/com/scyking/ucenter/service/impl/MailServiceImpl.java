package com.scyking.ucenter.service.impl;

import com.scyking.ucenter.pojo.model.Mail;
import com.scyking.ucenter.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author scyking
 **/
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public Mail sendMail(Mail mail) {
        checkMail(mail);
        try {
            sendMimeMail(mail);
        } catch (Exception e) {
            log.error("发送邮件失败：", e);
            mail.setStatus("fail");
            mail.setError(e.getMessage());
        }
        return mail;
    }

    @Override
    public void checkMail(Mail mail) {
        Assert.hasText(mail.getTo(), "邮箱收信人不能为空");
        Assert.hasText(mail.getSubject(), "邮件主题不能为空");
        Assert.hasText(mail.getText(), "邮件内容不能为空");
    }

    @Override
    public void saveMail(Mail mail) {

    }

    private String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }

    private void sendMimeMail(Mail mail) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            mail.setFrom(getMailSendFrom());
            messageHelper.setFrom(mail.getFrom());
            messageHelper.setTo(mail.getTo().split(","));
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText());
            if (StringUtils.hasText(mail.getCc())) {
                messageHelper.setCc(mail.getCc().split(","));
            }
            if (StringUtils.hasText(mail.getBcc())) {
                messageHelper.setCc(mail.getBcc().split(","));
            }
            if (mail.getMultipartFiles() != null) {
                for (MultipartFile multipartFile : mail.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            mail.setSentDate(LocalDateTime.now());
            messageHelper.setSentDate(new Date());
            mailSender.send(messageHelper.getMimeMessage());
            mail.setStatus("ok");
            log.info("发送邮件成功：{}->{}", mail.getFrom(), mail.getTo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
