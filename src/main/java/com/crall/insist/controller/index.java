package com.crall.insist.controller;

import com.crall.insist.utils.HandleWeiBoTop;
import com.crall.insist.utils.intrface.HandleHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class index {
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
        List<Map<String, Object>> sourceList = handleWeiBoTop.handleSampleHtml(url);
        map.put("weiboTopList", sourceList);
        return map;
    }
}
