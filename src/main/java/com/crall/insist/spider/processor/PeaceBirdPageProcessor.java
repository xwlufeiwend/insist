package com.crall.insist.spider.processor;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.crall.insist.spider.pipline.ConsolePipeLine;
import com.crall.insist.spider.pipline.PeaceBirdPipeLine;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeaceBirdPageProcessor implements PageProcessor {
    private static final String URL_LIST = "http://www\\.peacebirdwomen\\.com/basics/basics\\.php\\?id=311";

    private static List<Map<String, String>> category = new ArrayList<>();

    //配置site
    private Site site = Site.me().setSleepTime(3000).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");

    @Override
    public void process(Page page) {
//        System.out.println(page.getHtml());
//        System.out.println(URL_LIST);
        if (page.getUrl().regex(URL_LIST).match()){
            List<String> coverImgs = page.getHtml().xpath("//div[@class='basics_new']//img/@src").all();
//            page.putField("coverImgs", coverImgs);
            List<String> xilie = page.getHtml().xpath("//div[@class='basics_new']//p/span/text()").all();
//            page.putField("xilie", xilie);
            List<String> url = page.getHtml().xpath("//div[@class='basics_new']//a/@href").all();
            List<Map<String, String>> list = new ArrayList<>();
            for (int i = 0; i < coverImgs.size(); i++) {
                Map<String, String> map = new HashMap<>();
                map.put("url", url.get(i));
                map.put("coverImg", coverImgs.get(i));
                map.put("xilie", xilie.get(i));
                list.add(map);
            }
            page.putField("indexInfo",list);
            category = list;
            page.addTargetRequests(url);
        }else {
//            Map<String, String> img = new HashMap<>();
            /*if (category != null){
                for (Map<String, String> m : category) {

                }
            }*/
            page.putField(page.getUrl().get(),page.getHtml().xpath("//div[@class='pro_show']//img/@data-original").all());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        //取消日志打印
        Logger logger = (Logger) LoggerFactory.getLogger("org.apache.http");
        logger.setLevel(Level.INFO);
        logger.setAdditive(false);

        Spider.create(new PeaceBirdPageProcessor()).addPipeline(new ConsolePipeLine()).addPipeline(new PeaceBirdPipeLine()).addUrl("http://www.peacebirdwomen.com/basics/basics.php?id=311").thread(3).run();
    }
}
