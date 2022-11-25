package com.crall.insist.spider.pipline;

import com.crall.insist.utils.HttpDownloadUtil;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用于爬虫图片的下载
 */
public class PeaceBirdPipeLine implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        //首页
        List<Map<String,String>> indexInfo = resultItems.get("indexInfo");
        //图片URL
        if (indexInfo != null) {
            System.out.println(indexInfo);
            List<String> coverImgs = new ArrayList<>();
            List<String> xilies = new ArrayList<>();
            //下载封面
            for (Map<String,String> m:indexInfo) {
                String xilie = m.get("url").substring(m.get("url").lastIndexOf("=") + 1)+ "-" + m.get("xilie");
                xilies.add(xilie);
                String coveImg = m.get("coverImg");
                coverImgs.add(coveImg);
            }
            // 创建独立线程下载封面
            Runnable runnable = () -> {
                HttpDownloadUtil.downloadPictures(coverImgs, xilies, "peacebird");
            };
            Thread thread = new Thread(runnable);
            thread.start();

            /*for (Map<String, String> map : indexInfo) {
                String url = map.get("url");
                List<String> imgUrl = resultItems.get(url);
                System.out.println(imgUrl);
            }*/
        }
        String url = resultItems.getRequest().getUrl();
        List<String> imgUrls = resultItems.get(url);
        if (imgUrls != null){
            // 创建独立线程下载目标文件
            Runnable runnable = () -> {
                HttpDownloadUtil.downloadPictures(imgUrls, url.substring(url.lastIndexOf("=")+1),"peacebird");
            };
            Thread thread1 = new Thread(runnable);
            thread1.start();
//            System.out.println("imgurls"+ imgUrls);
        }
    }
}
