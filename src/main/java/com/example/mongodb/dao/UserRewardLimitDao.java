package com.example.mongodb.dao;

import com.example.mongodb.domain.UserRewardLimit;

/**
 * @author huanglong
 * @Description: ${todo}
 * @date 2018/10/2616:30
 */
public interface UserRewardLimitDao extends CommonDao<UserRewardLimit>{

    void decRewardNum(String pin,Integer activityId);

    void addActivityLimit(String pin,Integer activityId);
}
