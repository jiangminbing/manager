package com.soft.manager.service;


import com.soft.parent.basic.req.ArticleSerachDto;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.dao.ArticleMapper;
import com.soft.parent.manager.po.Article;
import com.soft.parent.manager.po.ArticleExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public PageResult<Article> getArticleByPage(ArticleSerachDto  dto) throws Exception{
        PageResult<Article> pageResult = new PageResult<>(ResCode.SUCCESS);
        ArticleExample example = createCriteria(dto);
        long total = articleMapper.countByExample(example);
        if(total>0){
            List<Article> list = articleMapper.selectByExample(example);
            pageResult.setData(list);
            pageResult.setTotal(total);
            return pageResult;
        }
        return pageResult;
    }
    public Article getArticleById(Integer id) throws Exception{
        Article article = articleMapper.selectByPrimaryKey(id);
        return  article;
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
