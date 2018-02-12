package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.ShoppingCartService;
import com.soft.manager.service.UserPrivilegeService;
import com.soft.manager.service.UserReceiveService;
import com.soft.manager.service.UserService;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import com.soft.parent.manager.po.Receive;
import com.soft.parent.manager.po.ShoppingCart;
import com.soft.parent.manager.po.User;
import com.soft.parent.manager.po.UserPrivilege;
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

    @Autowired
    private UserReceiveService userReceiveService;

    /**
     * 电话号码获取用户信息
     * @param moblie
     * @return
     */
    @RequestMapping(value = "/getUserByMobile",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result getUserByMobile(@RequestParam String moblie){
        try{
            DetailResult<User> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(userService.getUserByMobile(moblie));
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new Result(ResCode.SYS_ERR);
        }
    }

    /**
     * 通过openId查询用户信息
     * @param openId
     * @return
     */
    @RequestMapping(value = "/getUserByOpenId",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<User> getUserByOpenId(@RequestParam String openId){
        try{
            DetailResult<User> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(userService.getUserByOpenId(openId));
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult(ResCode.SYS_ERR);
        }
    }

    /**
     * 获取用户默认收货地址
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserDefaultReceive",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<Receive> getUserDefaultReceive(@RequestParam Integer userId){
        try{
           return userReceiveService.getUserDefaultReceive(userId);
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult(ResCode.SYS_ERR);
        }

    }

    @RequestMapping(value = "/getUserPrivilegeByUser",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<UserPrivilege> getUserPrivilegeByUser(@RequestParam Integer userId){
        try{
            DetailResult<UserPrivilege> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(userPrivilegeService.getUserPrivilegeByUser(userId));
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }

    }
    @RequestMapping(value = "/queryShoppingCartByUserIdAndPriceId",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public DetailResult<ShoppingCart> queryShoppingCartByUserIdAndPriceId(@RequestParam Integer userId, @RequestParam Integer priceId){
        try{
            DetailResult<ShoppingCart> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(shoppingCartService.getShoppingCartByUserIdAndPriceId(userId,priceId));
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}", JSON.toJSONString(e));
            return new DetailResult<>(ResCode.SYS_ERR);
        }
    }
}
