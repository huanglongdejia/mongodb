package com.example.mongodb.bean;

/**
 * @author huanglong
 * @Description: ${todo}
 * @date 2018/10/2910:48
 */
public class PinRewardLimit {

    private String pin;
    private Integer shareNum;
    private Integer sharedNum;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getSharedNum() {
        return sharedNum;
    }

    public void setSharedNum(Integer sharedNum) {
        this.sharedNum = sharedNum;
    }
}
