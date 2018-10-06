package com.ocr.picread.helper;

import com.ocr.picread.controller.IDCardController;
import com.ocr.picread.entity.ImgInfo;
import com.ocr.picread.utils.OpenCVImgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class IDCardReadHelper {

    private final static Logger logger = LoggerFactory.getLogger(IDCardController.class);

    private static final Double avatarSpacePer = 0.16;

    public static ImgInfo loadImg(ImgInfo imgInfo, String cardFile) {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.load("D:\\ImgRead\\opencv\\build\\java\\x64\\opencv_java320.dll");
        int[] rectPosition = OpenCVImgUtil.detectFace(cardFile);
        imgInfo.setX(rectPosition[0]);
        imgInfo.setY(rectPosition[1]);
        imgInfo.setW(rectPosition[2]);
        imgInfo.setH(rectPosition[3]);
        return imgInfo;
    }

    public static void cutNameImg(ImgInfo imgInfo, String cardFile, String nameFile) {
        int x = imgInfo.getX();
        int y = imgInfo.getY();
        int w = imgInfo.getW();
        int h = imgInfo.getH();
        int[] imgRect = new int[2];
        try {
            imgRect = OpenCVImgUtil.getImageWidth(cardFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (x == 0 || y == 0 || w == 0 || h == 0) {
            logger.info("人脸识别失败:" + cardFile);
            x = imgRect[0];
            y = imgRect[1];
            OpenCVImgUtil.cutImage(cardFile, nameFile, (int)(x * 0.17), (int)(y * 0.068), (int)(x * 0.25), (int)(y * 0.165));
        }else{
            OpenCVImgUtil.cutImage(cardFile, nameFile, (int)(x * 0.265), (int)(y * 0.38), (int)(x * 0.37), (int)(y * 0.52));
        }
    }

    public static void cutCardNoImg(ImgInfo imgInfo, String cardFile, String cardNoFile) {
        int x = imgInfo.getX();
        int y = imgInfo.getY();
        int w = imgInfo.getW();
        int h = imgInfo.getH();
        int[] imgRect = new int[2];
        try {
            imgRect = OpenCVImgUtil.getImageWidth(cardFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (x == 0 || y == 0 || w == 0 || h == 0) {
            logger.info("人脸识别失败:" + cardFile);
            x = imgRect[0];
            y = imgRect[1];
            OpenCVImgUtil.cutImage(cardFile, cardNoFile, (int)(x*0.32), (int)(y*0.8), (int)(x*0.61), (int)(y*0.16));
        }else{
            int cutX = x / 2 - 20;
            int width = imgRect[0] - cutX - 30;
            int cutHeight = 140;
            int imgHieght = imgRect[1];
            int height = (int) (imgHieght * avatarSpacePer);
            int cutY = y + h + height;
            if (imgHieght - cutY < cutHeight - 5) {
                cutY = imgHieght - (int) (imgHieght / 3.8) - 10;
            }
            if (imgHieght < 500) {
                cutY = cutY + 20;
            }
            OpenCVImgUtil.cutImage(cardFile, cardNoFile, (int)(cutX*1.05), (int)(cutY*1.04), (int)(width*0.90), (int)(cutHeight*0.71));
        }
    }
}
