package com.scyking.ucenter.service;

import com.scyking.ucenter.pojo.model.Mail;

/**
 * @author scyking
 **/
public interface MailService {

    Mail sendMail(Mail mail);

    void checkMail(Mail mail);

    void saveMail(Mail mail);
}
