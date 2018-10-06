package com.ocr.picread.entity;

public class IdCardManageInfo extends IdCardInfo {

    private int type;

    //身份证图片url
    private String cardUrl;

    //身份证原图位置
    private String cardFile;

    //姓名原图位置
    private String nameFile;

    //身份证原图位置
    private String cardNoFile;

    //姓名处理图位置
    private String nameDealFile;

    //身份证处理图位置
    private String cardNoDealFile;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCardUrl() {
        return cardUrl;
    }

    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }

    public String getCardFile() {
        return cardFile;
    }

    public void setCardFile(String cardFile) {
        this.cardFile = cardFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getCardNoFile() {
        return cardNoFile;
    }

    public void setCardNoFile(String cardNoFile) {
        this.cardNoFile = cardNoFile;
    }

    public String getNameDealFile() {
        return nameDealFile;
    }

    public void setNameDealFile(String nameDealFile) {
        this.nameDealFile = nameDealFile;
    }

    public String getCardNoDealFile() {
        return cardNoDealFile;
    }

    public void setCardNoDealFile(String cardNoDealFile) {
        this.cardNoDealFile = cardNoDealFile;
    }
}
