package com.ocr.picread.service.impl;

import com.ocr.picread.common.TesseractLanguage;
import com.ocr.picread.entity.IdCardInfo;
import com.ocr.picread.entity.IdCardManageInfo;
import com.ocr.picread.entity.ImgInfo;
import com.ocr.picread.helper.IDCardReadHelper;
import com.ocr.picread.helper.TesseractORC;
import com.ocr.picread.service.IdcardService;
import com.ocr.picread.utils.OpenCVImgUtil;
import org.springframework.stereotype.Component;

@Component
public class IdcardBlackServiceImpl implements IdcardService {

    @Override
    public IdCardInfo read(IdCardManageInfo idCardManageInfo) {
        ImgInfo imgInfo = new ImgInfo();
        String cardFile = idCardManageInfo.getCardFile();
        String cardNoDealFile =idCardManageInfo.getCardNoDealFile();
        String cardNoFile =idCardManageInfo.getCardNoFile();
        String nameDealFile =idCardManageInfo.getNameDealFile();
        String nameFile =idCardManageInfo.getNameFile();
        IDCardReadHelper.loadImg(imgInfo, cardFile);
        //截取姓名图片
        IDCardReadHelper.cutNameImg(imgInfo, cardFile, nameFile);
        //图片转换成黑白
        OpenCVImgUtil.convertBackWhiteImage(nameFile,nameDealFile);
        //获取黑白处理后图片中姓名
        idCardManageInfo.setName(TesseractORC.doOCR(nameDealFile, TesseractLanguage.CHI));
        //同理操作身份证号码
        IDCardReadHelper.cutCardNoImg(imgInfo, cardFile, cardNoFile);
        OpenCVImgUtil.convertBackWhiteImage(cardNoFile,cardNoDealFile);
        idCardManageInfo.setCardNo(TesseractORC.doOCR(cardNoDealFile, TesseractLanguage.ENM));
        return idCardManageInfo;
    }
}
