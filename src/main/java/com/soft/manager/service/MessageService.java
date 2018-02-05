package com.soft.manager.service;

import com.soft.manager.dao.MessageMapper;
import com.soft.manager.po.Message;
import com.soft.parent.basic.res.MessageDto;
import com.soft.parent.basic.result.DetailResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author jiangmb
 * @Time 2018/2/5.
 */
@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public DetailResult<MessageDto> getMessageById(@RequestParam Integer messageId){
        DetailResult<MessageDto> result = new DetailResult<>();
        Message message = messageMapper.selectByPrimaryKey(messageId);
        MessageDto dto = new MessageDto();
        BeanUtils.copyProperties(message,dto);
        result.setData(dto);
        return result;

    }
}
