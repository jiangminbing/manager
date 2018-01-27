package com.soft.manager.font;

import com.alibaba.fastjson.JSON;
import com.soft.manager.service.ArticleService;
import com.soft.parent.basic.req.ArticleSerachDto;
import com.soft.parent.basic.res.ArticleDto;
import com.soft.parent.basic.result.DetailResult;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author jiangmb
 * @Time 2018/1/27.
 */
@Controller
@RequestMapping(value = "/manager")
public class ArticleFontController extends BaseFontContrller {

    public ArticleFontController() {
        super("ArticleFontController");
    }
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/getArticleByPage" ,method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public PageResult<ArticleDto> getArticleByPage(@RequestBody ArticleSerachDto dto){
        try {
            printParam("getArticleByPage==>"+ JSON.toJSONString(dto));
            PageResult<ArticleDto> result = articleService.getArticleByPage(dto);
            return  result;
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new PageResult<>(ResCode.SYS_ERR);
        }
    }

    /**
     * 文章详情况
     * @param id
     * @return
     */
    @RequestMapping(value = "/getArticleById",method = {RequestMethod.GET,RequestMethod.POST})
    public Result getArticleById(@RequestParam Integer id) {
        try {
            printParam("getArticleById==>"+ JSON.toJSONString(id));
            ArticleDto articleDto = articleService.getArticleById(id);
            DetailResult<ArticleDto> result = new DetailResult<>(ResCode.SUCCESS);
            result.setData(articleDto);
            return result;
        }catch (Exception e){
            logger.error("系统异常:{}",JSON.toJSONString(e));
            return new Result(ResCode.SYS_ERR);
        }
    }


}
