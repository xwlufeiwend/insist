package com.crall.insist.utils;

import java.util.*;
import java.util.stream.Collectors;

public class GroupByMapKey {
    public static List<String> dealMap(Map<String, String> respMap){
        List<Map<String, String>> targetList = new ArrayList<>();
        int totalRecords = Integer.parseInt(respMap.get("data.totalRecords"));
        for (int i = 1; i < totalRecords + 1; i++){
            Map<String, String> target = new HashMap<>();
            String flag = respMap.get("data.responseBody.list[" + i + "].flag");
            if ("1".equals(flag)) {
                //正常
                Set<Map.Entry<String, String>> entries = respMap.entrySet();
                for (Map.Entry<String, String> e : entries){
                    String key = e.getKey();
                    if (key.startsWith("data.responseBody.list[" + i + "]")){
                        target.put(key.substring(key.lastIndexOf(".") + 1), respMap.get(key));
                    }
                }
                targetList.add(target);
            }
        }
        List<String> idNo1 = targetList.stream().map(m -> m.get("idNo")).collect(Collectors.toList());
        return idNo1;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("data.responseBody.list[1].idNo", "123456");
        map.put("data.responseBody.list[1].name", "张三");
        map.put("data.responseBody.list[1].age", "22");
        map.put("data.responseBody.list[1].flag", "1");

        map.put("data.responseBody.list[2].idNo", "222222");
        map.put("data.responseBody.list[2].name", "王二");
        map.put("data.responseBody.list[2].age", "32");
        map.put("data.responseBody.list[2].flag", "1");

        map.put("data.responseBody.list[3].idNo", "333333");
        map.put("data.responseBody.list[3].name", "李四");
        map.put("data.responseBody.list[3].age", "19");
        map.put("data.responseBody.list[3].flag", "1");

        //总条数
        map.put("data.totalRecords", "3");

        dealMap(map);
    }

}
