package com.crall.insist.utils;

import com.crall.insist.utils.intrface.HandleHtml;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;


/**
 * 处理HTML页面内容
 */
@Component
public class HandleWeiBoTop implements HandleHtml {

    @Override
    public List<Map<String, Object>> handleSampleHtml(String url) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            String sourceHtml = HttpClientUtils.getUrl(url, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
//            System.out.println(sourceHtml);
            // 创建Document对象
            Document document = Jsoup.parse(sourceHtml);

            //获取热搜榜
            Elements tr = document.getElementsByTag("tr");
            tr.remove(0);
            tr.remove(tr.size() - 1);
            for (Element td: tr) {
                Map<String, Object> map = new HashMap<>();
                Element td02 = td.getElementsByClass("td-02").first();
//                System.out.println(td02);
                Element a = td02.getElementsByTag("a").first();
                String topName = a.html();
                map.put("topName", topName);
                String href = a.getElementsByAttribute("href").first().attr("href");
                map.put("href", href);
                String rank = "0";
                if(td02.getElementsByTag("span").size() > 0){
                    rank = td02.getElementsByTag("span").first().html();
                }
                map.put("rank", rank);
                list.add(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
