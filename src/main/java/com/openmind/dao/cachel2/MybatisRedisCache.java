package com.openmind.dao.cachel2;

import com.openmind.util.SpringContextHolder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MybatisRedisCache implements Cache {
    private static final Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private RedisTemplate<String, Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");

    private String id;

    public MybatisRedisCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        if (o1 == null) {
            redisTemplate.opsForValue().set(o.toString(), o1, 60, TimeUnit.SECONDS);
        }
    }

    @Override
    public Object getObject(Object o) {
        if (o == null) {
            return null;
        }

        final Object o1 = redisTemplate.opsForValue().get(o.toString());
        return o1;
    }

    @Override
    public Object removeObject(Object o) {
        if (o == null) {
            return null;
        }

        redisTemplate.delete(o.toString());
        return null;
    }

    @Override
    public void clear() {//todo
        Set<String> keys = redisTemplate.keys("*:" + this.id + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    @Override
    public int getSize() {
        Long size = (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
        return size.intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
