package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.MessageService;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import com.soft.parent.manager.po.Message;
import com.soft.parent.manager.po.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/2/8.
 */
@Controller
@RequestMapping(value = "/manager/")
public class MessageController extends BaseFontContrller{

    public MessageController() {
        super("MessageController");
    }
    @Autowired
    private MessageService messageService;
    @RequestMapping(value = "getMessageById",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<Message> getMessageById(@RequestParam Integer messageId){
        try{
            DetailResult<Message> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(messageService.getMessageById(messageId));
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
    @RequestMapping(value = "getUserMessageById",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<UserMessage> getUserMessageById(@RequestParam Integer userMessageId){
        try{
            DetailResult<UserMessage> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(messageService.getUserMessageId(userMessageId));
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }

    }
    @RequestMapping(value = "updateUserMessage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result updateUserMessage(@RequestBody UserMessage userMessage){

        try{
            boolean res = messageService.updateUserMessage(userMessage);
            if(!res)return new Result(ResCode.FAILED);
            return new Result(ResCode.SUCCESS);

        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
    @RequestMapping(value = "updateUserMessage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<List<Message>> getMessageLevel(@RequestParam Integer userId){
        try{
            return messageService.getMessageLevel(userId);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
    @RequestMapping(value = "getNoReadCount",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<Long> getNoReadCount(@RequestParam Integer userId){
        try{
            return messageService.getNoReadCount(userId);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
    @RequestMapping(value = "getMessageCount",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<Long> getMessageCount(@RequestParam Integer userId){
        try{
            return messageService.getMessageCount(userId);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
}
