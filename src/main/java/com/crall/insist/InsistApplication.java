package com.crall.insist;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan(value = "com.crall.insist.dao.mapper")
public class InsistApplication {
    public static void main(String[] args) {
        /*Logger logger = (Logger) LoggerFactory.getLogger("org.apache.http");
        logger.setLevel(Level.INFO);
        logger.setAdditive(false);*/
        SpringApplication.run(InsistApplication.class, args);
    }

}
