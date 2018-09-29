package com.ocr.picread.service;

import com.ocr.picread.entity.IdCardInfo;
import com.ocr.picread.entity.IdCardManageInfo;

/**
 * 身份证识别服务
 */
public interface IdcardService {

    /**
     *
     * @param idCardManageInfo 身份证manage
     * @return
     */
    IdCardInfo read(IdCardManageInfo idCardManageInfo);
}
