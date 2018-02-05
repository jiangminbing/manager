package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.ShoppingCartService;
import com.soft.manager.service.UserPrivilegeService;
import com.soft.manager.service.UserService;
import com.soft.parent.basic.res.ShoppingCartDto;
import com.soft.parent.basic.res.UserPrivilegeDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserPrivilegeService userPrivilegeService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 电话号码获取用户信息
     * @param moblie
     * @return
     */
    @RequestMapping(value = "/getUserByMobile",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result getUserByMobile(@RequestParam String moblie){
        try{
            return userService.getUserByMobile(moblie);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new Result(ResCode.SYS_ERR);
        }
    }
    @RequestMapping(value = "/getUserPrivilegeByUser",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<UserPrivilegeDto> getUserPrivilegeByUser(@RequestParam Integer userId){
        try{
            return userPrivilegeService.getUserPrivilegeByUser(userId);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }

    }
    @RequestMapping(value = "/queryShoppingCartByUserIdAndPriceId",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<ShoppingCartDto> queryShoppingCartByUserIdAndPriceId(@RequestParam Integer userId,@RequestParam Integer priceId){
        try{
            return shoppingCartService.queryShoppingCartByUserIdAndPriceId(userId,priceId);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
}
