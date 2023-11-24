package com.crall.insist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/web/api")
public class RegisterController {
    @PostMapping("/register")
    @ResponseBody
    public Map<String, String> register(@RequestBody Map<String, String> requestMap){
        System.out.println(requestMap.toString());
        Map<String, String> res = new HashMap<>();
        res.put("resCode", "0000");
        res.put("resMessage", "注册成功！");
        return res;
    }
}
