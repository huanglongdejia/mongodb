package com.example.mongodb;

import com.example.mongodb.dao.ActivityRewardLimitDao;
import com.example.mongodb.dao.UserRewardLimitDao;
import com.example.mongodb.domain.ActivityRewardLimit;
import com.example.mongodb.domain.UserRewardLimit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbApplicationTests {


    @Autowired
    private UserRewardLimitDao userRewardLimitDao;

    @Autowired
    private ActivityRewardLimitDao activityRewardLimitDao;

    @Test
    public void contextLoads() {
    }

    @Test
    public void save() {
        UserRewardLimit userRewardLimit = new UserRewardLimit();
        userRewardLimit.setPin("huanglongdejia");
        userRewardLimit.setActivityId(100);
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
    }

    @Test
    public void dec() {
        userRewardLimitDao.decRewardNum("huanglongdejia",100);
    }

    @Test
    public void addActivityLimit() {
        userRewardLimitDao.addActivityLimit("hl",90);
    }

    @Test
    public void saveActivityLimit() {

        Integer dayLimit = 10;
        Integer totalLimit = 20;
        Integer shareLimit = 4;
        Integer sharedLimit = 5;
        Random r = new Random();
        Date start = new Date();
        for(int i=1;i<=2;i++){
            ActivityRewardLimit activityRewardLimit = new ActivityRewardLimit();
            activityRewardLimit.setActivityId(i);
            activityRewardLimit.setDayLimit(dayLimit);
            activityRewardLimit.setTotalLimit(totalLimit);
            activityRewardLimit.setDayNum(r.nextInt(dayLimit+1));
            activityRewardLimit.setTotalNum(r.nextInt(totalLimit+1));
            List<ActivityRewardLimit.PinLimit> pinLimits = new ArrayList<>();
            for(int j=1;j<=2;j++){
                ActivityRewardLimit.PinLimit pinLimit = new ActivityRewardLimit.PinLimit();
                pinLimit.setPin("hl");
                List<UserRewardLimit> userRewardLimits = new ArrayList<>();
                for(int k=1;k<=2;k++){
                    UserRewardLimit userRewardLimit = new UserRewardLimit();
                    userRewardLimit.setPin("huanglongdejia");
                    userRewardLimit.setActivityId(i);
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

                pinLimits.add(pinLimit);
            }
            activityRewardLimit.setPinLimits(pinLimits);
            activityRewardLimitDao.save(activityRewardLimit);
        }
        Date end = new Date();
        System.out.println("耗时："+(end.getTime()-start.getTime())/1000);
    }

    @Test
    public void decReward() {
        activityRewardLimitDao.decRewardNum("huanglongdejia",1);
    }

    @Test
    public void addPinLimit() {
        activityRewardLimitDao.addPinLimit("huanglongdejia",2);
    }

    @Test
    public void resertTodayNum() {
        activityRewardLimitDao.resertTodayNum(1);
    }

    @Test
    public void resertActivityTodayNum() {
        activityRewardLimitDao.resertActivityTodayNum();
    }

    @Test
    public void getActivityLimit() {
        ActivityRewardLimit activityRewardLimit = activityRewardLimitDao.queryById("5bd7bb7b5d89b230b8c07d12");
        System.out.println(activityRewardLimit);
    }
}
