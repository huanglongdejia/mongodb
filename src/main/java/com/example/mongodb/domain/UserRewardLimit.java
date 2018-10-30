package com.example.mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author huanglong
 * @Description: 每个人的奖励限制
 * @date 2018/10/2616:12
 */
@Document(collection = "user_reward_limit")
public class UserRewardLimit {

    @Id
    private String id;
    private String pin;
    private Integer activityId;
    private PinLimit shareLimit;
    private PinLimit sharedLimit;

    public static class PinLimit{

        private Integer todayNum;
        private Integer totalNum;
        private Integer todayLimit;
        private Integer totalLimit;

        public Integer getTodayNum() {
            return todayNum;
        }

        public void setTodayNum(Integer todayNum) {
            this.todayNum = todayNum;
        }

        public Integer getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(Integer totalNum) {
            this.totalNum = totalNum;
        }

        public Integer getTodayLimit() {
            return todayLimit;
        }

        public void setTodayLimit(Integer todayLimit) {
            this.todayLimit = todayLimit;
        }

        public Integer getTotalLimit() {
            return totalLimit;
        }

        public void setTotalLimit(Integer totalLimit) {
            this.totalLimit = totalLimit;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public PinLimit getShareLimit() {
        return shareLimit;
    }

    public void setShareLimit(PinLimit shareLimit) {
        this.shareLimit = shareLimit;
    }

    public PinLimit getSharedLimit() {
        return sharedLimit;
    }

    public void setSharedLimit(PinLimit sharedLimit) {
        this.sharedLimit = sharedLimit;
    }
}
