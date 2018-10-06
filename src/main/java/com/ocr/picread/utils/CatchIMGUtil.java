package com.ocr.picread.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CatchIMGUtil {

    private final static Logger logger = LoggerFactory.getLogger(CatchIMGUtil.class);

    /**
     *
     * @Title: getImg
     * @Description: 通过一个url 去获取图片
     * @param @param url 图片的连接地址
     * @param @throws IOException
     * @throws
     */
    public static void getImg(String url,String fileurl) throws IOException {
        double  startTime = System.currentTimeMillis();
        URL imgURL = new URL(url.trim());//转换URL
        HttpURLConnection urlConn = (HttpURLConnection) imgURL.openConnection();//构造连接
        urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.79 Safari/537.36");
        urlConn.connect();
        logger.info("获取["+url+"]连接="+urlConn.getResponseMessage());
        if(urlConn.getResponseCode()==200){//返回的状态码是200 表示成功
            InputStream ins = urlConn.getInputStream(); //获取输入流,从网站读取数据到 内存中
            OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(fileurl)));
            int len=0;
            byte[] buff = new byte[1024*10];//10k缓冲流
            while(-1!=(len=(new BufferedInputStream(ins)).read(buff))){//长度保存到len,内容放入到 buff
                out.write(buff, 0, len);//将图片数组内容写入到图片文件
            }
            urlConn.disconnect();
            ins.close();
            out.close();
            double  endTime = System.currentTimeMillis();
            logger.info("获取["+url+"]图片完成,耗时="+((endTime-startTime)/1000)+"s");
        }
    }
}
