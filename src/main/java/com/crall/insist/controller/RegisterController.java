package com.crall.insist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/web/api")
public class RegisterController {

    private final static Set<String> accs = new HashSet<>();
    private final static List<Map<String, Map<String, String>>> accList = new ArrayList<>();

    @PostMapping("/register")
    @ResponseBody
    public Map<String, String> register(@RequestBody Map<String, String> requestMap, HttpServletRequest request){
        System.out.println(requestMap.toString());
        Map<String, String> res = new HashMap<>();
        String account = requestMap.get("account");
        boolean flag = accs.add(account);
        if (flag){
            Map<String, Map<String, String>> a = new HashMap<>();
            a.put(account, requestMap);
            accList.add(a);
        }else {
            res.put("resCode", "1111");
            res.put("resMessage", "注册失败！，账号已存在！");
            return res;
        }
        res.put("resCode", "0000");
        res.put("resMessage", "注册成功！");
        return res;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> requestMap, HttpServletRequest request){
        System.out.println(requestMap.toString());
        Map<String, String> res = new HashMap<>();
        String account = requestMap.get("account");
        if (accs.contains(account)){
            // 账号存在！
            Map<String, String> acc = accList.stream().map(map -> map.get(account)).findFirst().get();
            System.out.println(acc);
            String password = requestMap.get("password");
            String pwd = acc.get("password");
            if (pwd.equals(password)){
                //密码正确
                res.put("resCode", "0000");
                request.getSession().setAttribute("account", account);
            }else {
                res.put("resCode", "1111");
                res.put("resMessage", "账号密码错误，登录失败！！！");
            }
        }else {
            res.put("resCode", "1112");
            res.put("resMessage", "账号不存在！！！");
        }
        return res;
    }
}
