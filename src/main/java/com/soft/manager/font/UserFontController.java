package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.UserService;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author jiangmb
 * @Time 2018/2/1.
 */
@Controller
@RequestMapping(value = "/manager")
public class UserFontController extends BaseFontContrller {

    public UserFontController() {
        super("UserFontController");
    }
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param moblie
     * @param psw
     * @return
     */
    @RequestMapping(value = "/getGoodsByPage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result login(@RequestParam String moblie, @RequestParam String psw){
        try{
            return userService.login(moblie,psw);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new Result(ResCode.SYS_ERR);
        }
    }
}
