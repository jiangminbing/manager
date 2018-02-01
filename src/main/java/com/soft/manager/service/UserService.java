package com.soft.manager.service;

import com.alibaba.fastjson.JSON;
import com.soft.manager.dao.UserMapper;
import com.soft.manager.po.User;
import com.soft.manager.po.UserExample;
import com.soft.parent.basic.enums.PoolEnum;
import com.soft.parent.basic.redis.RedisConnection;
import com.soft.parent.basic.redis.RedisUtil;
import com.soft.parent.basic.res.UserDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import com.soft.parent.basic.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/1.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisConnection redisConnection;

    /**
     * 用户登录
     * @param mobile
     * @param psw
     * @return
     * @throws Exception
     */
    public Result login(String mobile, String psw)throws Exception{
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(mobile);
        criteria.andPswEqualTo(psw);
        List<User> list = userMapper.selectByExample(example);
        if(list!=null||list.isEmpty()) return new Result(ResCode.USER_EXISTS);
        DetailResult<UserDto> result = new DetailResult<>(ResCode.SUCCESS);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(dto,list.get(0));
        String token = saveUserRedis(dto);
        dto.setToken(token);
        result.setData(dto);
        return result;
    }

    /**
     * 保存用户信息到缓存中 30分钟内有效
     * @param user
     * @throws Exception
     */
    protected String saveUserRedis(UserDto user) throws Exception{
        String token = UUIDUtil.getUUID();
        JedisPool jedisPool = redisConnection.getPool(PoolEnum.USER_TOKEN.getPool());
        RedisUtil.saveStringByEx(jedisPool,PoolEnum.USER_TOKEN.getKey()+token,JSON.toJSONString(user),30);
        return token;
    }
}
