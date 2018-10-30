package com.example.mongodb.dao.impl;

import com.example.mongodb.dao.CommonDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huanglong
 * @Description: ${todo}
 * @date 2018/10/2616:47
 */
public abstract class CommonDaoImpl<T> implements CommonDao<T> {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 保存一个对象
     */
    @Override
    public void save(T t){
        this.mongoTemplate.save(t);
    }

    /**
     * 根据Id从Collection中查询对象
     */
    @Override
    public T queryById(String id){
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 根据条件查询集合
     */
    @Override
    public List<T> queryList(Query query){
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 通过条件查询单个实体
     */
    @Override
    public T queryOne(Query query){
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 通过条件进行分页查询
     */
    @Override
    public List<T> getPage(Query query, int start, int size){
        query.skip(start);
        query.limit(size);
        List<T> lists = this.mongoTemplate.find(query, this.getEntityClass());
        return lists;
    }

    /**
     * 根据条件查询库中符合记录的总数,为分页查询服务
     */
    @Override
    public Long getPageCount(Query query){
        return this.mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * 根据Id删除
     */
    @Override
    public void deleteById(String id){
        Criteria criteria = Criteria.where("_id").in(id);
        if (null != criteria){
            Query query = new Query(criteria);
            if (null != query && this.queryOne(query) != null)
            {
                this.mongoTemplate.remove(query);
            }
        }
    }

    /**
     * 更新满足条件的第一个记录
     */
    @Override
    public void updateFirst(Query query, Update update){
        this.mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }

    /**
     * 更新满足条件的所有记录
     */
    @Override
    public void updateMulti(Query query, Update update){
        this.mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     */
    @Override
    public void updateInser(Query query, Update update){
        this.mongoTemplate.upsert(query, update, this.getEntityClass());
    }

    /**
     * 删除对象
     */
    @Override
    public void delete(T t){
        this.mongoTemplate.remove(t);
    }

    /**
     * 为属性自动注入bean服务
     */
    @Override
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    protected abstract Class<T> getEntityClass();
}
