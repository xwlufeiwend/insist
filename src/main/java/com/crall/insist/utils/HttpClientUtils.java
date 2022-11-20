package com.crall.insist.utils;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtils {
    public static String getUrl(String url, String header) throws IOException {
        // header 支持数组和Header对象
        // 创建默认httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //访问url
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("use-agent", header);
        httpGet.setHeader("cookie","SUB=_2AkMUJB0Uf8NxqwJRmPEdymzmbItzyQ_EieKieOzPJRMxHRl-yT92qk86tRB6P6Qz-7-uAjcfj6i75KBKjgSxmkOwIhyk; SUBP=0033WrSXqPxfM72-Ws9jqgMF55529P9D9WFTLepmAYa2Sm10WGy1AQGj; _s_tentry=passport.weibo.com; Apache=9496801407286.674.1668846116488; SINAGLOBAL=9496801407286.674.1668846116488; ULV=1668846116493:1:1:1:9496801407286.674.1668846116488:");
        httpGet.setHeader("referer","https://s.weibo.com/top/summary?cate=socialevent");
        //获取响应结果
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        String html="";
        if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
            html = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        }
        httpClient.close();
        closeableHttpResponse.close();
        return html;
    }

    public CloseableHttpResponse postUrl(String url, String header) throws IOException {
        // header 支持数组和Header对象
        // 创建默认httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //访问url
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("use-agent", header);

        //获取响应结果
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
        httpClient.close();
        return closeableHttpResponse;
    }
}
