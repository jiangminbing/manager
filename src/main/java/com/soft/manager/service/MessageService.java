package com.soft.manager.service;


import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.dao.MessageMapper;
import com.soft.parent.manager.dao.MessageMapperEx;
import com.soft.parent.manager.dao.UserMessageMapper;
import com.soft.parent.manager.po.Message;
import com.soft.parent.manager.po.UserMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author jiangmb
 * @Time 2018/2/5.
 */
@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Autowired
    private MessageMapperEx messageMapperEx;
    /*用户最新消息的数量*/
    private final static int top = 5;

    public Message getMessageById(Integer messageId){
        Message message = messageMapper.selectByPrimaryKey(messageId);
        return message;
    }
    public UserMessage getUserMessageId(Integer userMessageId){
        UserMessage userMessage = userMessageMapper.selectByPrimaryKey(userMessageId);
        return userMessage;
    }
    public boolean updateUserMessage(UserMessage userMessage) throws Exception{
        return userMessageMapper.updateByPrimaryKeySelective(userMessage)>0;
    }

    /**
     * 获取用户最新状态的消息
     * @param userId
     * @return
     * @throws Exception
     */
    public DetailResult<List<Message>> getMessageLevel(Integer userId)throws Exception{
        DetailResult<List<Message>> result = new DetailResult<>(ResCode.SUCCESS);
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("top",top);
        List<Message> list = messageMapperEx.selectMessageTop(map);
        result.setData(list);
        return result;
    }

    /**
     * 用户未未读消息
     * @param userId
     * @return
     * @throws Exception
     */
    public DetailResult<Long> getNoReadCount(Integer userId)throws Exception{
        DetailResult<Long> result = new DetailResult<>(ResCode.SUCCESS);
        long total = messageMapperEx.countNoReadCount(userId);
        if(total>0)result.setData(total);
        else result.setData(Long.valueOf(0));
        return result;
    }

    /**
     * 计算用户所用消息
     * @param userId
     * @return
     * @throws Exception
     */
    public DetailResult<Long> getMessageCount(Integer userId) throws Exception{
        DetailResult<Long> result = new DetailResult<>(ResCode.SUCCESS);
        long total = messageMapperEx.countMessage(userId);
        if(total>0)result.setData(total);
        else result.setData(Long.valueOf(0));
        return result;
    }
}
