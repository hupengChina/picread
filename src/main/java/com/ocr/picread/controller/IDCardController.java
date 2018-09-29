package com.ocr.picread.controller;

import com.ocr.picread.entity.IdCardInfo;
import com.ocr.picread.entity.IdCardManageInfo;
import com.ocr.picread.service.impl.IdCardServiceImpl;
import com.ocr.picread.service.impl.IdcardBlackServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IDCardController {

    private final static Logger logger = LoggerFactory.getLogger(IDCardController.class);

    @Resource
    IdcardBlackServiceImpl idcardBlackServiceImpl;

    @Resource
    IdCardServiceImpl idCardServiceImpl;

    @RequestMapping("/getIdCardFront/{type}")
    public IdCardInfo getIdCardFront(@PathVariable int type, IdCardInfo idCardInfo) {
        IdCardManageInfo idCardManageInfo = new IdCardManageInfo();
//        idCardManageInfo.setCardUrl("F:\\test\\test.jpg");
//        idCardManageInfo.setCardFile("F:\\test\\test.jpg");
//        idCardManageInfo.setNameDealFile("F:\\test\\name1.jpg");
//        idCardManageInfo.setNameFile("F:\\test\\name2.jpg");
//        idCardManageInfo.setCardNoDealFile("F:\\test\\test1.jpg");
//        idCardManageInfo.setCardNoFile("F:\\test\\test2.jpg");
        //原图识别（默认）
        if(type == 0){
            idCardServiceImpl.read(idCardManageInfo);
        }else if(type == 1){//转换黑白识别
            idcardBlackServiceImpl.read(idCardManageInfo);
        }
        return idCardInfo;
    }
}
