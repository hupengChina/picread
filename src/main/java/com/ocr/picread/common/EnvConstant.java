package com.ocr.picread.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 环境设置常量
 */
@Configuration
@PropertySource(value = { "classpath:/envonstant.properties"})
public class EnvConstant {

    public static final String  TESSDATA = "D:\\ImgRead\\Tesseract-OCR\\tessdata";
    public static final String  FACEDETECTIONS = "D:\\ImgRead\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml";
    public static final String  UPLOADFILEPATH = "/home/card/";

}
