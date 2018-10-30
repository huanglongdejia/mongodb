package com.example.mongodb.dao;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @author huanglong
 * @Description: ${todo}
 * @date 2018/10/2616:46
 */
public interface CommonDao<T> {

    /**
     * 保存一个对象
     */
    void save(T t);
    /**
     * 根据Id从Collection中查询对象
     */
    T queryById(String id);
    /**
     * 根据条件查询集合
     */
    List<T> queryList(Query query);
    /**
     * 通过条件查询单个实体
     */
    T queryOne(Query query);
    /**
     * 通过条件进行分页查询
     */
    List<T> getPage(Query query, int start, int size);
    /**
     * 根据条件查询库中符合记录的总数,为分页查询服务
     */
    Long getPageCount(Query query);
    /**
     * 根据Id删除
     */
    void deleteById(String id);
    /**
     * 更新满足条件的第一个记录
     */
    void updateFirst(Query query, Update update);
    /**
     * 更新满足条件的所有记录
     */
    void updateMulti(Query query, Update update);
    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     */
    void updateInser(Query query, Update update);

    /**
     * 删除对象
     */
    void delete(T t);

    /**
     * 为属性自动注入bean服务
     */
    void setMongoTemplate(MongoTemplate mongoTemplate);
}
