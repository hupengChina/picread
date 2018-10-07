package com.ocr.picread.entity;

import java.io.Serializable;

/**
 * 身份证信息
 */
public class IdCardInfo implements Serializable{

    //姓名
    private String name;

    //身份证号
    private String cardNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
