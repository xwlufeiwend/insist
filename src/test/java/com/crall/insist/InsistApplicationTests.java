package com.crall.insist;

import com.crall.insist.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class InsistApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(InsistApplicationTests.class);

    @Autowired
    JedisPool getJedisPool;
    @Test
    void contextLoads() {
        Jedis resource = getJedisPool.getResource();
        resource.set("test-j", "20230108利世");
        logger.info("测试获取test-j：{}", resource.get("test-j").toString());
        //System.out.println(resource.get("test-j"));
        resource.close();
    }

}
