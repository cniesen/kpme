package org.kuali.kpme.tklm.time.clocklog;

import org.apache.commons.lang.StringUtils;

public class RemoteSwipeData {

    public static final String START_SENTINEL = ";";
    public static final String END_SENTINEL = "?";

    private String startSentinel; // ';'
    private String isoNumber;
    private String uid;
    private String cardVersionNo;
    private String checkDigit; // ' '
    private String fieldSeparator; // '='
    private String expirationYear;
    private String expirationMonth;
    private String cardTypeCode;
    private String filler; // '00000000'
    private String pinOffset;
    private String endSentinel;
// private String longitudinalRedundancyCheck;

    public RemoteSwipeData() {

    }

    public RemoteSwipeData(String decryptedData) {

        this.startSentinel = StringUtils.substring(decryptedData, 0, 1);
        this.isoNumber = StringUtils.substring(decryptedData, 1, 7);
        this.uid = StringUtils.substring(decryptedData, 7, 16);
        this.cardVersionNo = StringUtils.substring(decryptedData, 16, 17);
        this.checkDigit = StringUtils.substring(decryptedData, 17, 18); // ' '
        this.fieldSeparator = StringUtils.substring(decryptedData, 18, 19); // '='
        this.expirationYear = StringUtils.substring(decryptedData, 19, 21);
        this.expirationMonth = StringUtils.substring(decryptedData, 21, 23);
        this.cardTypeCode = StringUtils.substring(decryptedData, 23, 26);
        this.filler = StringUtils.substring(decryptedData, 26, 34); // '00000000'
        this.pinOffset = StringUtils.substring(decryptedData, 34, 38);
        this.endSentinel = StringUtils.substring(decryptedData, 38, 39);

        //TODO: FIX - TESTING
        //this.uid = "10049";
    }

    public String getStartSentinel() {
        return startSentinel;
    }

    public void setStartSentinel(String startSentinel) {
        this.startSentinel = startSentinel;
    }

    public String getIsoNumber() {
        return isoNumber;
    }

    public void setIsoNumber(String isoNumber) {
        this.isoNumber = isoNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCardVersionNo() {
        return cardVersionNo;
    }

    public void setCardVersionNo(String cardVersionNo) {
        this.cardVersionNo = cardVersionNo;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(String checkDigit) {
        this.checkDigit = checkDigit;
    }

    public String getFieldSeparator() {
        return fieldSeparator;
    }

    public void setFieldSeparator(String fieldSeparator) {
        this.fieldSeparator = fieldSeparator;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getCardTypeCode() {
        return cardTypeCode;
    }

    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getPinOffset() {
        return pinOffset;
    }

    public void setPinOffset(String pinOffset) {
        this.pinOffset = pinOffset;
    }

    public String getEndSentinel() {
        return endSentinel;
    }

    public void setEndSentinel(String endSentinel) {
        this.endSentinel = endSentinel;
    }


}