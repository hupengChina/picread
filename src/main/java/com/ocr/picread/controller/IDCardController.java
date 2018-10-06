package com.ocr.picread.controller;

import com.alibaba.fastjson.JSONObject;
import com.ocr.picread.common.EnvConstant;
import com.ocr.picread.common.IdCardConstant;
import com.ocr.picread.entity.IdCardInfo;
import com.ocr.picread.entity.IdCardManageInfo;
import com.ocr.picread.service.impl.IdCardServiceImpl;
import com.ocr.picread.service.impl.IdcardBlackServiceImpl;
import com.ocr.picread.utils.CatchIMGUtil;
import com.ocr.picread.utils.DeleteFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RestController
public class IDCardController {

    private final static Logger logger = LoggerFactory.getLogger(IDCardController.class);

    @Resource
    IdcardBlackServiceImpl idcardBlackServiceImpl;

    @Resource
    IdCardServiceImpl idCardServiceImpl;

    @RequestMapping("/getIdCardFront")
    public IdCardInfo getIdCardFront(@RequestBody IdCardManageInfo idCardManageInfo) throws InvocationTargetException, IllegalAccessException {
        long enterTime = System.currentTimeMillis();
        String path = EnvConstant.UPLOADFILEPATH+enterTime;
        idCardManageInfo.setCardFile(path+IdCardConstant.cardFile);
        try {
            CatchIMGUtil.getImg(idCardManageInfo.getCardUrl(),idCardManageInfo.getCardFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        idCardManageInfo.setNameDealFile(path+IdCardConstant.nameDealFile);
        idCardManageInfo.setNameFile(path+IdCardConstant.nameFile);
        idCardManageInfo.setCardNoDealFile(path+IdCardConstant.cardNoDealFile);
        idCardManageInfo.setCardNoFile(path+IdCardConstant.cardNoFile);
        //原图识别（默认）
        if(idCardManageInfo.getType() == 0){
            idCardServiceImpl.read(idCardManageInfo);
        }else if(idCardManageInfo.getType() == 1){//转换黑白识别
            idcardBlackServiceImpl.read(idCardManageInfo);
        }
        DeleteFileUtil.deleteDirectory(path);
        String ob = JSONObject.toJSONString(idCardManageInfo);
        IdCardInfo idCardInfo = (IdCardInfo)JSONObject.parseObject(ob, IdCardInfo.class);
        return idCardInfo;
    }
}
