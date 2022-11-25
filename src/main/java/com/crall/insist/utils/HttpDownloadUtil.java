package com.crall.insist.utils;

import ch.qos.logback.core.util.FileUtil;
import us.codecraft.webmagic.Task;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

public class HttpDownloadUtil {
    private static final int cache = 10 * 1024;
    // D:\wabmagic
    private static String baseUrl = "D:\\webmagic\\";

    static {
        File file = new File(baseUrl);
        if (!file.exists()){
            file.mkdir();
        }
    }
    /**
     * 下载一组图片带名字
     * @param urlList
     * @param names
     * @param diskUrl 二级目录
     */
    public static void downloadPictures(List<String> urlList, List<String> names, String diskUrl){
        URL url = null;
        try{
            for (int i = 0; i < urlList.size(); i++) {
                //创建目标url对象
                url = new URL(urlList.get(i));
                //打开数据流
                DataInputStream dataInputStream = new DataInputStream(url.openStream());
                String fileUrl = baseUrl + diskUrl + "\\" + names.get(i) + "\\";
                File target = new File(fileUrl);
                if (!target.exists()){
                    target.mkdirs();
                }
                String fileName = urlList.get(i).substring(urlList.get(i).lastIndexOf("/") + 1);
                File targetFile = new File(target, fileName);
                FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
                byte[] buffer = new byte[1024 * 50];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0){
                    fileOutputStream.write(buffer, 0, length);
                }
                System.out.println("已经下载：" + fileUrl + "\\" + fileName);
                dataInputStream.close();
                fileOutputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 单目录多文件
     * @param urlList
     * @param name 三级目录名
     * @param diskUrl
     */
    public static void downloadPictures(List<String> urlList, String name, String diskUrl){
        URL url = null;
        String fileUrl = baseUrl + diskUrl + "\\" + name + "\\";
        File target = new File(fileUrl);
        try{
            if (!target.exists()){
                target.mkdirs();
            }
            for (int i = 0; i < urlList.size(); i++) {
                //创建目标url对象
                url = new URL(urlList.get(i));
                String fileName = urlList.get(i).substring(urlList.get(i).lastIndexOf("/") + 1);
                File targetFile = new File(target, fileName);
                //打开数据流
                DataInputStream dataInputStream = new DataInputStream(url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(targetFile);
                byte[] buffer = new byte[1024 * 50];
                int length;
                while ((length = dataInputStream.read(buffer)) > 0){
                    fileOutputStream.write(buffer, 0, length);
                }
                System.out.println("已经下载：" + target + "\\" + fileName);
                dataInputStream.close();
                fileOutputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }



}
