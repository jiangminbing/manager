package com.soft.manager.service;

import com.soft.manager.dao.ArticleMapper;
import com.soft.manager.po.Article;
import com.soft.manager.po.ArticleExample;
import com.soft.parent.basic.req.ArticleSerachDto;
import com.soft.parent.basic.res.ArticleDto;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/1/27.
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 分页查询
     * @param dto
     * @return
     * @throws Exception
     */
    public PageResult<ArticleDto> getArticleByPage(ArticleSerachDto  dto) throws Exception{
        ArticleExample example = createCriteria(dto);
        long total = articleMapper.countByExample(example);
        if(total>0){
            List<Article> list = articleMapper.selectByExample(example);
            List<ArticleDto> resList = new ArrayList<>();
            for(Article article:list){
                ArticleDto articleDto = new ArticleDto();
                BeanUtils.copyProperties(article,articleDto);
                resList.add(articleDto);
            }
            PageResult<ArticleDto> pageResult = new PageResult<>(ResCode.SUCCESS);
            pageResult.setData(resList);
            pageResult.setTotal(total);
            return pageResult;
        }
        return new PageResult<ArticleDto>(ResCode.NO_DATA);
    }
    public ArticleDto getArticleById(Integer id) throws Exception{
        Article article = articleMapper.selectByPrimaryKey(id);
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(articleDto ,article);
        return  articleDto;
    }
    public ArticleExample createCriteria(ArticleSerachDto dto) throws Exception{
        ArticleExample example = new ArticleExample();
        if(StringUtils.isNotEmpty(dto.getOrderBy())){
            if(!dto.getReverse()) {
                example.setOrderByClause(dto.getOrderBy()+" desc");
            }else {
                example.setOrderByClause(dto.getOrderBy());
            }
        }
        if(dto.getPageSize()!=null){
            example.setBegin(example.getBegin());
            example.setLength(example.getLength());
        }
        ArticleExample.Criteria criteria = example.createCriteria();
        return example;

    }
}
