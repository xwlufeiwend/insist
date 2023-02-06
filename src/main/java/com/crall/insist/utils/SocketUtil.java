package com.crall.insist.utils;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * socket通讯工具类
 */
public class SocketUtil {
    private String hostNameIp = "127.0.0.1";

    private Integer port = 8090;

    // 客户端发送数据
    private String clientSendMsg(String msg){
        try {
            Socket clientSocket = new Socket(hostNameIp ,port);
            OutputStream clientSocketOutputStream = clientSocket.getOutputStream();
            //字节缓冲流
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(clientSocketOutputStream);
            bufferedOutputStream.write(msg.getBytes(StandardCharsets.UTF_8));
            bufferedOutputStream.flush();

            BufferedInputStream bufferedInputStream = new BufferedInputStream(clientSocket.getInputStream());
            int by = 0;
            // 通过其他方式 获取输入流中的内容 字节 字符 等
            byte[] bytes = new byte[1024];
            if ((by = bufferedInputStream.read(bytes)) != -1){
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
            }

            if ((by = bufferedInputStream.read()) != -1){
                System.out.println((char) by);
            }
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocketOutputStream));
//            bufferedWriter.write("123");
//            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

    }
}
