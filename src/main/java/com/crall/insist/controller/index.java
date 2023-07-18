package com.crall.insist.controller;

import com.alibaba.fastjson.JSONObject;
import com.crall.insist.aspects.annotation.MyLog;
import com.crall.insist.service.IndexService;
import com.crall.insist.utils.HandleTianqi;
import com.crall.insist.utils.HandleWeiBoTop;
import com.crall.insist.utils.intrface.HandleHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
public class index {
    //依赖自动注入
    @Autowired
    private IndexService indexService;

    @Autowired
    private JedisPool jedisPool;
//    private HandleWeiBoTop handleWeiBoTop;
    @GetMapping("/index")
    public Map<String, Object> indexInfo(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "首页");
        map.put("info","我希望你坚持下去，为了自己！");
        return map;
    }

    @GetMapping("/list")
    public Map<String, Object> list(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        String url = "https://s.weibo.com/top/summary?cate=realtimehot";
        HandleHtml handleWeiBoTop = new HandleWeiBoTop();
        Jedis resource = jedisPool.getResource();
        if (resource.get("sourceList") == null) {
            List<Map<String, Object>> sourceList = handleWeiBoTop.handleSampleHtml(url);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("weiBoList", sourceList);
            resource.set("sourceList", jsonObject.toJSONString());
        }
        System.out.println(resource.get("sourceList"));
        map.put("weiboTopList", resource.get("sourceList"));
        //List<Map<String, Object>> sourceList = handleWeiBoTop.handleSampleHtml(url);
        //map.put("weiboList", sourceList);

        resource.close();
        return map;
    }

    @GetMapping("/weather")
    @MyLog(value = "weather")
    public Map<String, Object> getWeather(HttpServletRequest request){
        String dateStr = request.getParameter("dateStr");
        Map<String, Object> map = new HashMap<String, Object>();
        HandleHtml handleTianqi = new HandleTianqi();
        String url = "https://www.tianqi.com/wuhan";
        Jedis resource = jedisPool.getResource();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-mm-dd");
        String format = simpleDateFormat.format(new Date());
        String key = "weather" + format;
        System.out.println("日期" + format);
        if (resource.get(key) == null){
            Map<String, Object> map1 = handleTianqi.handleMapHtml(url);
            resource.set(key, JSONObject.toJSONString(map1));
        }
        map.put("weather", resource.get(key));
        indexService.sendEmailtoMe(resource.get(key));
        resource.close();
        return map;
    }
}
