package com.ocr.picread.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:idcardconstant.properties"})
@ConfigurationProperties(prefix = "idcardconstant")
public class IdCardConstant {

    public static  String  nameDealFile;
    public static String  cardFile;
    public static String  nameFile;
    public static String  cardNoFile ;
    public static String  cardNoDealFile;

    public String getNameDealFile() {
        return nameDealFile;
    }

    public void setNameDealFile(String nameDealFile) {
        this.nameDealFile = nameDealFile;
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

    public String getCardNoDealFile() {
        return cardNoDealFile;
    }

    public void setCardNoDealFile(String cardNoDealFile) {
        this.cardNoDealFile = cardNoDealFile;
    }
}
