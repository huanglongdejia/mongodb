package com.example.mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author huanglong
 * @Description: 活动的奖励限制
 * @date 2018/10/299:52
 */
@Document(collection = "activity_reward_limit")
public class ActivityRewardLimit {
    @Id
    private String id;
    private Integer activityId;
    private String activityName;
    private Integer dayLimit;
    private Integer totalLimit;
    private Integer dayNum;
    private Integer totalNum;
    private List<PinLimit> pinLimits;
    public static class PinLimit{
        private String pin;
        @DBRef
        private List<UserRewardLimit> userRewardLimits;

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public List<UserRewardLimit> getUserRewardLimits() {
            return userRewardLimits;
        }

        public void setUserRewardLimits(List<UserRewardLimit> userRewardLimits) {
            this.userRewardLimits = userRewardLimits;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(Integer dayLimit) {
        this.dayLimit = dayLimit;
    }

    public Integer getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Integer totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Integer getDayNum() {
        return dayNum;
    }

    public void setDayNum(Integer dayNum) {
        this.dayNum = dayNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public List<PinLimit> getPinLimits() {
        return pinLimits;
    }

    public void setPinLimits(List<PinLimit> pinLimits) {
        this.pinLimits = pinLimits;
    }
}
