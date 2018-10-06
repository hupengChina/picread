package com.ocr.picread.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 环境设置常量
 */
@Configuration
@PropertySource(value = { "classpath:envconstant.properties"})
@ConfigurationProperties(prefix = "envconstant")
public class EnvConstant {

    public static String tessdata;

    public static String frontalface;

    public static String uploadfilepath;

    public String getTessdata() {
        return tessdata;
    }

    public void setTessdata(String tessdata) {
        this.tessdata = tessdata;
    }

    public String getFrontalface() {
        return frontalface;
    }

    public void setFrontalface(String frontalface) {
        this.frontalface = frontalface;
    }

    public String getUploadfilepath() {
        return uploadfilepath;
    }

    public void setUploadfilepath(String uploadfilepath) {
        this.uploadfilepath = uploadfilepath;
    }
}
