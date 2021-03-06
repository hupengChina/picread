package com.ocr.picread.helper;

import com.ocr.picread.config.EnvConstant;
import net.sourceforge.tess4j.Tesseract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Component
public class TesseractORC {

    private final static Logger logger = LoggerFactory.getLogger(TesseractORC.class);

    /**
     *
     * @param srImage 图片路径
     * @param language 训练库语言
     * @return 识别结果
     */
    public static synchronized  String doOCR(String srImage, String language) {
        try {
            File imageFile = new File(srImage);
            if (!imageFile.exists()) {
                logger.error("图片不存在");
                return "图片不存在";
            }
            BufferedImage textImage = ImageIO.read(imageFile);
            //Tesseract instance=Tesseract.getInstance();//单例并发报错
            Tesseract instance= new Tesseract();
            instance.setDatapath(EnvConstant.tessdata);//设置训练库
            instance.setLanguage(language);
            instance.setPageSegMode(7);//单行识别
            String result = instance.doOCR(textImage).replaceAll("\r|\n|\t|[\\p{Punct}\\pP]", "");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发生未知错误");
            return "发生未知错误";
        }
    }
}
