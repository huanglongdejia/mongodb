package com.example.mongodb.dao;

import com.example.mongodb.domain.ActivityRewardLimit;

/**
 * @author huanglong
 * @Description: ${todo}
 * @date 2018/10/2911:05
 */
public interface ActivityRewardLimitDao extends CommonDao<ActivityRewardLimit> {

    void decRewardNum(String pin, Integer activityId);

    void addPinLimit(String pin,Integer activityId);

    void resertTodayNum(Integer activityId);

    void resertActivityTodayNum();
}
