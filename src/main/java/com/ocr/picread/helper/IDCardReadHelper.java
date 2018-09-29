package com.ocr.picread.helper;

import com.ocr.picread.common.TesseractLanguage;
import com.ocr.picread.controller.IDCardController;
import com.ocr.picread.entity.ImgInfo;
import com.ocr.picread.utils.OpenCVImgUtil;
import org.opencv.core.Core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class IDCardReadHelper {

    private final static Logger logger = LoggerFactory.getLogger(IDCardController.class);

    private static final Double avatarSpacePer = 0.16;
    private static final Double avatarPer = 0.28;

    public static ImgInfo loadImg(ImgInfo imgInfo, String cardFile) {
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
            int cutX = x / 3 - 20;
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
            OpenCVImgUtil.cutImage(cardFile, nameFile, (int)(x * 0.19), (int)(y * 0.1), (int)(x * 0.24), (int)(x * 0.1));
        }else{
            int cutX = x / 2 - 20;
            int width = imgRect[0] - cutX - 30;
            int cutHeight = 140;
            int imgHieght = imgRect[1];
            int topSpace = (int) (imgHieght * avatarPer);
            int height = (int) (imgHieght * avatarSpacePer);
            int cutY = y + h + height;
            if (imgHieght - cutY < cutHeight - 5) {
                cutY = imgHieght - (int) (imgHieght / 3.8) - 10;
            }
            if (imgHieght < 500) {
                cutY = cutY + 20;
            }
            OpenCVImgUtil.cutImage(cardFile, nameFile, (int)(x * 0.28), (int)(x * 0.1), (int)(x * 0.24), (int)(x * 0.1));
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
            int cutX = x / 3 - 20;
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
            OpenCVImgUtil.cutImage(cardFile, cardNoFile, (int)(cutX*1.05), (int)(cutY*1.05), (int)(width*0.90), (int)(cutHeight*0.70));
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
            OpenCVImgUtil.cutImage(cardFile, cardNoFile, (int)(cutX*1.05), (int)(cutY*1.05), (int)(width*0.90), (int)(cutHeight*0.70));
        }
    }





    /**
     * @param args
     */
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        for(int i = 1; i <2; i++){
            double start=System.currentTimeMillis();
            double end=System.currentTimeMillis();
            logger.info("*******************耗时"+(end-start)/1000+" s");
        }
    }

    private static void load(String fileName) {
        String suffix = ".jpg";
        int[] rectPosition = OpenCVImgUtil.detectFace(fileName);
        int x = rectPosition[0];
        int y = rectPosition[1];
        int w = rectPosition[2];
        int h = rectPosition[3];
        int[] imgRect = new int[2];
        try {
            imgRect = OpenCVImgUtil.getImageWidth(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (x == 0 || y == 0 || w == 0 || h == 0) {
            String destFile = "F:\\test\\test" + suffix;
            OpenCVImgUtil.convertBackWhiteImage(fileName, destFile);
            //test(destFile);
            x = imgRect[0];
            y = imgRect[1];
            w = (int) (x * avatarPer);
            h = w;
            logger.info("人脸识别失败:" + fileName + " 采用默认识别");
            int cutX = x / 3 - 20;
            int width = imgRect[0] - cutX - 30;
            int cutHeight = 140;
            int imgHieght = imgRect[1];
            int topSpace = (int) (imgHieght * avatarPer);
            int height = (int) (imgHieght * avatarSpacePer);
            int cutY = y + h + height;
            if (imgHieght - cutY < cutHeight - 5) {
                cutY = imgHieght - (int) (imgHieght / 3.8) - 10;
            }
            if (imgHieght < 500) {
                cutY = cutY + 20;
            }
            logger.info("--------未处理图片识别结果:");
            OpenCVImgUtil.cutImage(fileName, "F:\\test\\name1.jpg", (int)(x * 0.19), (int)(y * 0.1), (int)(x * 0.24), (int)(x * 0.1));
            logger.info("姓名:" + TesseractORC.doOCR("F:\\test\\name1.jpg", TesseractLanguage.CHI));
            OpenCVImgUtil.cutImage(fileName, "F:\\test\\test1.jpg", (int)(cutX*1.05), (int)(cutY*1.05), (int)(width*0.90), (int)(cutHeight*0.70));
            logger.info("身份证号:" + Test.test("F:\\test\\test1.jpg"));

            logger.info("--------图片转换黑白识别结果:");
            OpenCVImgUtil.convertBackWhiteImage("F:\\test\\name1.jpg", "F:\\test\\name2.jpg");
            logger.info("姓名:" + TesseractORC.doOCR("F:\\test\\name1.jpg", TesseractLanguage.CHI));
            test("F:\\test\\test1.jpg");
        }else{
            OpenCVImgUtil.convertBackWhiteImage(fileName, "F:\\test\\test.jpg");
            int cutX = x / 2 - 20;

            String destFile = "F:\\test\\test1.jpg";
            int width = imgRect[0] - cutX - 30;
            int cutHeight = 140;
            int imgHieght = imgRect[1];
            int topSpace = (int) (imgHieght * avatarPer);
            int height = (int) (imgHieght * avatarSpacePer);
            int cutY = y + h + height;
            if (imgHieght - cutY < cutHeight - 5) {
                cutY = imgHieght - (int) (imgHieght / 3.8) - 10;
            }
            if (imgHieght < 500) {
                cutY = cutY + 20;
            }
            logger.info("--------未处理图片识别结果:");
            OpenCVImgUtil.cutImage(fileName, "F:\\test\\name1.jpg", (int)(x * 0.28), (int)(x * 0.1), (int)(x * 0.24), (int)(x * 0.1));
            logger.info("姓名:" + TesseractORC.doOCR("F:\\test\\name1.jpg",TesseractLanguage.CHI));
            OpenCVImgUtil.cutImage(fileName, destFile, (int)(cutX*1.05), (int)(cutY*1.05), (int)(width*0.90), (int)(cutHeight*0.70));
            logger.info("身份证号:" + Test.test(destFile));
            logger.info("--------图片转换黑白识别结果:");
            OpenCVImgUtil.convertBackWhiteImage("F:\\test\\name1.jpg", "F:\\test\\name2.jpg");
            logger.info("姓名:" + TesseractORC.doOCR("F:\\test\\name2.jpg",TesseractLanguage.CHI));
            test(destFile);
        }
    }

    private static void test(String fileName) {
        String suffix = ".jpg";
        int[] rectPosition = OpenCVImgUtil.detectFace(fileName);
        int x = rectPosition[0];
        int y = rectPosition[1];
        int w = rectPosition[2];
        int h = rectPosition[3];
        int[] imgRect = new int[2];
        try {
            imgRect = OpenCVImgUtil.getImageWidth(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (x == 0 || y == 0 || w == 0 || h == 0) {
            String destFile = "F:\\test\\test2" + suffix;
            OpenCVImgUtil.convertBackWhiteImage(fileName, destFile);
            x = imgRect[0];
            y = imgRect[1];
            w = (int) (x * avatarPer);
            h = w;
            logger.info("人脸识别失败:" + Test.test(destFile));
        }
    }

}
