package com.example.library.service;

import javax.mail.MessagingException;

public interface MailService {
    /***
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendSimpleMail(String to, String subject, String content);
}
