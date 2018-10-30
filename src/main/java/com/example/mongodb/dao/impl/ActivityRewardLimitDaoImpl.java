package com.example.mongodb.dao.impl;

import com.example.mongodb.bean.PinRewardLimit;
import com.example.mongodb.dao.ActivityRewardLimitDao;
import com.example.mongodb.dao.UserRewardLimitDao;
import com.example.mongodb.domain.ActivityRewardLimit;
import com.example.mongodb.domain.UserRewardLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huanglong
 * @Description: ${todo}
 * @date 2018/10/2911:06
 */
@Component
public class ActivityRewardLimitDaoImpl extends CommonDaoImpl<ActivityRewardLimit> implements ActivityRewardLimitDao {
    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRewardLimitDao userRewardLimitDao;

    @Override
    public void decRewardNum(String pin, Integer activityId) {

        Query query = new Query(Criteria.where("activityId").is(activityId))
                .addCriteria(Criteria.where("pinRewardLimits.pin").is(pin));
        Update update = new Update();
        update.inc("dayNum",1);
        update.inc("totalNum",1);
        update.inc("pinRewardLimits.$.shareNum",1);
        update.inc("pinRewardLimits.$.sharedNum",1);
        mongoTemplate.updateMulti(query, update, ActivityRewardLimit.class);

    }

    @Override
    public void addPinLimit(String pin,Integer activityId){

        Query query = new Query(Criteria.where("activityId").is(activityId));
        Update update = new Update();
        ActivityRewardLimit.PinLimit pinLimit = new ActivityRewardLimit.PinLimit();
        pinLimit.setPin(pin);

        List<UserRewardLimit> userRewardLimits = new ArrayList<>();
        for(int k=1;k<=2;k++){
            UserRewardLimit userRewardLimit = new UserRewardLimit();
            userRewardLimit.setPin(pin);
            userRewardLimit.setActivityId(activityId);
            UserRewardLimit.PinLimit sharePinLimit = new UserRewardLimit.PinLimit();
            UserRewardLimit.PinLimit sharedPinLimit = new UserRewardLimit.PinLimit();
            userRewardLimit.setShareLimit(sharePinLimit);
            userRewardLimit.setSharedLimit(sharedPinLimit);
            sharePinLimit.setTodayLimit(10);
            sharePinLimit.setTotalLimit(20);
            sharePinLimit.setTodayNum(5);
            sharePinLimit.setTotalNum(7);

            sharedPinLimit.setTodayLimit(10);
            sharedPinLimit.setTotalLimit(20);
            sharedPinLimit.setTodayNum(5);
            sharedPinLimit.setTotalNum(7);
            userRewardLimitDao.save(userRewardLimit);
            userRewardLimits.add(userRewardLimit);
        }

        pinLimit.setUserRewardLimits(userRewardLimits);
        update.push("pinLimits",pinLimit);
        mongoTemplate.updateMulti(query, update, ActivityRewardLimit.class);

    }

    public void resertTodayNum(Integer activityId) {

        Query query = new Query(Criteria.where("activityId").is(activityId))
                .addCriteria(Criteria.where("pinRewardLimits.shareNum").gt(0))
                .addCriteria(Criteria.where("pinRewardLimits.sharedNum").gt(0));
        Update update = new Update();
        update.set("pinRewardLimits.$.shareNum",0).set("pinRewardLimits.$.sharedNum",0)
                ;
        mongoTemplate.updateMulti(query, update, ActivityRewardLimit.class);
    }

    @Override
    public void resertActivityTodayNum() {
        Query query = new Query();
        Update update = new Update();
        update.set("dayNum",0);
        mongoTemplate.updateMulti(query, update, ActivityRewardLimit.class);
    }

    @Override
    protected Class<ActivityRewardLimit> getEntityClass() {
        return ActivityRewardLimit.class;
    }
}
