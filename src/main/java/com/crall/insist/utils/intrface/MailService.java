package com.crall.insist.utils.intrface;

public interface MailService {
    /**
     * 发送简单文本内容邮件
     * @param to
     * @param subject
     * @param content
     * @return
     */
    boolean sendSimpleText(String to, String subject, String content);

    /**
     * 发送html的邮件
     * @param to
     * @param subject
     * @param html
     * @return
     */
    boolean sendWithHtml(String to, String subject, String html);

    /**
     * 发送带有图片的html邮件
     * @param to
     * @param subject
     * @param html
     * @param cids
     * @param filePaths
     * @return
     */
    boolean sendWithImageHtml(String to, String subject, String html, String[] cids, String[] filePaths);

    /**
     * 发送带有附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePaths
     * @return
     */
    boolean sendWithEnclosure(String to, String subject, String content, String[] filePaths);
}
