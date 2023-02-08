package com.crall.insist.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * list集合的用法
 */
public class ListUtils {
    private static Logger logger = LoggerFactory.getLogger(ListUtils.class);

    public static void main(String[] args) {
        List<Map<String, Object>> xw = getListData("xw");
        List<Map<String, Object>> ls = getListData("ls");
        //printList(xw);
        //printList(ls);
        //将一个list放到另一个list的指定位置
        //取list中id=4的map元素的第一个值
        Optional<Map<String, Object>> id4 = xw.stream().filter(m -> m.get("id").toString().equals("4")).findFirst();
        id4.ifPresent(System.out::println);
        List<Map<String, Object>> mergeList = mergeTwoList(xw, ls, id4.get());

        Map<String, Object> totalData = new HashMap<>();
        double balance = xw.stream().mapToDouble(m -> Double.parseDouble(m.get("balance").toString())).sum();
        double score = xw.stream().mapToDouble(m -> Double.parseDouble(m.get("score").toString())).sum();
        String name = "合计数据";
        String id = "00";
        double averageAge = xw.stream().mapToDouble(m -> Double.parseDouble(m.get("age").toString())).average().orElse(0.00);
        totalData.put("id", id);
        totalData.put("balance", balance);
        totalData.put("score", score);
        totalData.put("name", name);
        totalData.put("averageAge", averageAge);
        //xw.add(totalData);
        Map<String, Object> map = xw.stream().filter(m -> m.get("name").toString().equals("xw0")).findFirst().get();
        xw.add(xw.indexOf(map), totalData);
        //printList(xw.stream().sorted((m1, m2) -> ((Double) Double.parseDouble(m1.get("balance").toString())).compareTo((Double) Double.parseDouble(m2.get("balance").toString()))).collect(Collectors.toList()));
        printList(xw);
    }

    private static List<Map<String, Object>> mergeTwoList(List<Map<String, Object>> xw, List<Map<String, Object>> ls,Map idMap) {
        //Map<String, Object> map = new HashMap<>();
        /*for (Map<String,Object> m : xw){
            if (m.get("id").toString().equals("4")){
                map = m;
            }
        }*/
        int index = xw.indexOf(idMap);
        logger.info("目标索引位置：{}", index);
        xw.addAll(index + 1, ls);
        return xw;
    }

    public static List<Map<String, Object>> getListData(String name){
        List<Map<String, Object>> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("id", i);
            map.put("name", name + i);
            map.put("age", i + 20);
            map.put("score", i * 10);
            map.put("balance", i * 100);
            list.add(map);
        }
        return list;
    }

    public static void printList(List<Map<String, Object>> list){
        list.forEach(m -> System.out.println(m.toString()));
    }
}
