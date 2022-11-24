package com.crall.insist.spider.processor;

import com.crall.insist.spider.pipline.ConsolePipeLine;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Map;

public class PeaceBirdPageProcessor implements PageProcessor {
    private static final String URL_LIST = "http://www\\.peacebirdwomen\\.com/basics/basics\\.php\\?id=311";


    //配置site
    private Site site = Site.me().setSleepTime(3000).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");

    @Override
    public void process(Page page) {
//        System.out.println(page.getHtml());
        System.out.println(URL_LIST);
        if (page.getUrl().regex(URL_LIST).match()){
            List<String> coverImgs = page.getHtml().xpath("//div[@class='basics_new']//img/@src").all();
            page.putField("coverImgs", coverImgs);
            page.addTargetRequests(page.getHtml().xpath("//div[@class='basics_new']//a/@href").all());
        }else {
            page.putField("imgSrcs",page.getHtml().xpath("//div[@class='pro_show']//img/@data-original").all());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new PeaceBirdPageProcessor()).addPipeline(new FilePipeline("D:\\wabmagic")).addPipeline(new ConsolePipeLine()).addUrl("http://www.peacebirdwomen.com/basics/basics.php?id=311").run();
    }
}
