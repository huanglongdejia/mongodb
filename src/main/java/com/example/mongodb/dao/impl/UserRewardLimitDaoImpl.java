package com.example.mongodb.dao.impl;

import com.example.mongodb.dao.UserRewardLimitDao;
import com.example.mongodb.domain.UserRewardLimit;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author huanglong
 * @Description: ${todo}
 * @date 2018/10/2616:51
 */
@Component
public class UserRewardLimitDaoImpl extends CommonDaoImpl<UserRewardLimit> implements UserRewardLimitDao {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void decRewardNum(String pin, Integer activityId) {
        Query query = new Query(Criteria.where("_id").is(pin))
                .addCriteria(Criteria.where("activityLimits.activityId").is(activityId));
        Update update = new Update();
        update.inc("activityLimits.$.shareLimit",-1);
        mongoTemplate.updateMulti(query, update, UserRewardLimit.class);
    }

    public void addActivityLimit(String pin,Integer activityId){
        Query query = new Query(Criteria.where("activityId").is(activityId))
                .addCriteria(Criteria.where("activityId").is(activityId));
        Update update = new Update();
        //UserRewardLimit.ActivityLimit activityLimit = new UserRewardLimit.ActivityLimit();
        //activityLimit.setActivityId(activityId);
       // activityLimit.setSharedLimit(3);
        //activityLimit.setShareLimit(3);
        //update.push("activityLimits",activityLimit);
        mongoTemplate.updateMulti(query, update, UserRewardLimit.class);
    }

    @Override
    protected Class<UserRewardLimit> getEntityClass() {
        return UserRewardLimit.class;
    }
}
