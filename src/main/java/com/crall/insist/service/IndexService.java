package com.crall.insist.service;

import com.crall.insist.utils.MySendMailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IndexService {

    private Logger logger = LoggerFactory.getLogger(IndexService.class);
    @Autowired
    private MySendMailUtils mySendMailUtils;
    public void sendEmailtoMe(Map<String, Object> map){
//        MySendMailUtils mySendMailUtils = new MySendMailUtils();
        StringBuilder stringBuilder = new StringBuilder(map.get("week").toString());
        stringBuilder.append("\n");
        stringBuilder.append(map.get("tq").toString());
        stringBuilder.append("  "+map.get("wd").toString());
        String str = new String(stringBuilder);
        logger.info("发送邮件内容："+str);
        mySendMailUtils.sendSimpleText("1364980105@qq.com","天气预报", str);
    }

    public void sendEmailtoMe(String weather){
        logger.info("weather: {}", weather);
    }
}
