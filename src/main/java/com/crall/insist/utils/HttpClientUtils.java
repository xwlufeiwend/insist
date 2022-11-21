package com.crall.insist.utils;

import com.crall.insist.entity.HeaderEntity;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtils {
    public static String getUrl(String url, HeaderEntity header) throws IOException {
        // header 支持数组和Header对象
        // 创建自定义的httpClient对象 更兼容访问网址
        HttpClientBuilder builder = HttpClients.custom();
        //这里必须设置userAgent 否则访问天气网失败
        builder.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        //构建httpClient
        CloseableHttpClient httpClient = builder.build();

        //访问url
        HttpGet httpGet = new HttpGet(url);
//        httpGet.setHeader("use-agent", header.getUseAgent());
        httpGet.setHeader("cookie", header.getCookie());
        httpGet.setHeader("referer", header.getReferer() != null ? header.getReferer() : "");
        httpGet.setHeader("accept", header.getAccept());
        /*httpGet.setHeader("sec-ch-ua", "\"Google Chrome\";v=\"107\", \"Chromium\";v=\"107\", \"Not=A?Brand\";v=\"24\"");
        httpGet.setHeader("sec-ch-ua-platform", "Windows");
        httpGet.setHeader("sec-fetch-dest", "document");
        httpGet.setHeader("sec-fetch-mode", "navigate");
        httpGet.setHeader("upgrade-insecure-requests", "1");*/
        //获取响应结果
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
//        Header[] allHeaders = closeableHttpResponse.getAllHeaders();
//        System.out.println(allHeaders.toString());
        String html="";
        if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
            html = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        }
        httpClient.close();
        closeableHttpResponse.close();
        return html;
    }

    public CloseableHttpResponse postUrl(String url, HeaderEntity header) throws IOException {
        // header 支持数组和Header对象
        // 创建默认httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //访问url
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("use-agent", header.getUseAgent());

        //获取响应结果
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
        httpClient.close();
        return closeableHttpResponse;
    }
}
