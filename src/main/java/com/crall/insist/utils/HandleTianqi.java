package com.crall.insist.utils;

import com.crall.insist.entity.HeaderEntity;
import com.crall.insist.utils.intrface.HandleHtml;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理天气界面
 */
public class HandleTianqi implements HandleHtml {
    @Override
    public List<Map<String, Object>> handleSampleHtml(String url) {
        return null;
    }

    @Override
    public Map<String, Object> handleMapHtml(String url) {
        Map<String, Object> map = new HashMap<>();
        //设置请求头
        HeaderEntity headerEntity = new HeaderEntity();
        headerEntity.setUseAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
        headerEntity.setCookie("Hm_lvt_ab6a683aa97a52202eab5b3a9042a8d2=1668995033; Hm_lvt_30606b57e40fddacb2c26d2b789efbcb=1668995042; cityPy=wuhan; cityPy_expire=1669599953; PATHURL=c=code&a=getcode&id=6&py=chongqing&icon=1; Hm_lpvt_30606b57e40fddacb2c26d2b789efbcb=1669010417; Hm_lpvt_ab6a683aa97a52202eab5b3a9042a8d2=1669010418");
        headerEntity.setReferer("https://www.tianqi.com");
        headerEntity.setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        try {
            //解析界面
            String sourceHtml = HttpClientUtils.getUrl(url, headerEntity);
//            System.out.println(sourceHtml);
            Document document = Jsoup.parse(sourceHtml);
            Element weather_info = document.getElementsByClass("weather_info").first();
//            System.out.println(weather_info);
            //获取日历信息
            String week = weather_info.getElementsByClass("week").first().html();
            map.put("week",week);// 日历
            //获取天气图标
            String imgSrc = weather_info.getElementsByTag("img").attr("src");
            map.put("imgSrc", imgSrc);// 天气图标
            //获取当前温度和单位
            String nowTemperature = weather_info.getElementsByClass("now").first().getElementsByTag("b").first().html();
            String symbol  = weather_info.getElementsByClass("now").first().getElementsByTag("i").first().html();
            map.put("nowTemperature", nowTemperature + symbol);
            //获取天气概况
            String str1 = weather_info.getElementsByTag("span").first().getElementsByTag("b").first().html();
            String span = weather_info.getElementsByTag("span").first().html();
            map.put("tq", str1);
            String wd = StringUtils.subTagBefore(span);
            map.put("wd", wd);
//            System.out.println(span);
            //获取湿度 风向 紫外线
            Elements shidub = weather_info.getElementsByClass("shidu").first().getElementsByTag("b");
            Map<String,Object> shidu = new HashMap<>();
            shidu.put("humidness", shidub.get(0).html());
            shidu.put("windDirection", shidub.get(1).html());
            shidu.put("ultravioletRay", shidub.get(2).html());
            map.put("shidu", shidu);
            //获取空气质量 PM 日出日落
            String kqzl = weather_info.getElementsByClass("kongqi").first().getElementsByTag("h5").first().html();
            map.put("kqzl", kqzl);
            String PM = weather_info.getElementsByClass("kongqi").first().getElementsByTag("h6").first().html();
            map.put("PM", PM);
            String rcrl = weather_info.getElementsByClass("kongqi").first().getElementsByTag("span").first().html();
            map.put("rcrl", rcrl);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return map;
    }
}
