package com.ocr.picread.service.impl;

import com.ocr.picread.common.TesseractLanguage;
import com.ocr.picread.entity.IdCardInfo;
import com.ocr.picread.entity.IdCardManageInfo;
import com.ocr.picread.entity.ImgInfo;
import com.ocr.picread.helper.IDCardReadHelper;
import com.ocr.picread.helper.TesseractORC;
import com.ocr.picread.service.IdcardService;
import org.springframework.stereotype.Component;

@Component
public class IdCardServiceImpl implements IdcardService {

    @Override
    public IdCardInfo read(IdCardManageInfo idCardManageInfo) {
        ImgInfo imgInfo = new ImgInfo();
        String cardFile = idCardManageInfo.getCardFile();
        String cardNoFile =idCardManageInfo.getCardNoFile();
        String nameFile =idCardManageInfo.getNameFile();
        IDCardReadHelper.loadImg(imgInfo, cardFile);
        //截取姓名图片
        IDCardReadHelper.cutNameImg(imgInfo, cardFile, nameFile);
        //获取图片中姓名
        idCardManageInfo.setName(TesseractORC.doOCR(nameFile, TesseractLanguage.CHI));
        //同理操作身份证号码
        IDCardReadHelper.cutCardNoImg(imgInfo, cardFile, cardNoFile);
        idCardManageInfo.setCardNo(TesseractORC.doOCR(cardNoFile, TesseractLanguage.ENG));
        return idCardManageInfo;
    }
}
