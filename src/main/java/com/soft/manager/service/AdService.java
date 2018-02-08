package com.soft.manager.service;


import com.soft.parent.basic.req.AdSearchDto;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.manager.dao.AdMapper;
import com.soft.parent.manager.po.Ad;
import com.soft.parent.manager.po.AdExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author jiangmb
 * @Time 2018/1/22.
 */
@Service
public class AdService {
    @Autowired
    private AdMapper adMapper;

    /**
     * 分页查询
     * @param dto
     * @return
     * @throws Exception
     */
    public PageResult<Ad>  getAdByPage(AdSearchDto dto) throws Exception{
        PageResult<Ad> pageResult = new PageResult<>(ResCode.SUCCESS);
        AdExample adExample = createCriteria(dto);
        long total = adMapper.countByExample(adExample);
        if(total>0){
            List<Ad> list = adMapper.selectByExample(adExample);
            pageResult.setData(list);
            pageResult.setTotal(total);
            return pageResult;
        }
        return pageResult;
    }

    /**
     * 通过Id查询
     * @param id
     * @return
     * @throws Exception
     */
    public Ad getAdById(Integer id) throws Exception{
        Ad ad = adMapper.selectByPrimaryKey(id);
        return  ad;
    }
    public AdExample createCriteria(AdSearchDto dto) throws Exception{
        AdExample example = new AdExample();
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
        AdExample.Criteria criteria = example.createCriteria();
        return example;

    }

}
