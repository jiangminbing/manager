package com.soft.manager.service;

import com.soft.manager.dao.AdMapper;
import com.soft.manager.po.AdExample;
import com.soft.parent.basic.req.AdSearchDto;
import com.soft.parent.basic.res.AdDto;
import com.soft.parent.basic.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author jiangmb
 * @Time 2018/1/22.
 */
@Service
public class AdService {
    @Autowired
    private AdMapper adMapper;

    public PageResult<AdDto>  getAdByPage(AdSearchDto dto) throws Exception{
        return null;
    }
    public AdExample.Criteria createCriteria(AdExample example,AdSearchDto dto) throws Exception{
        if(dto.getPageSize()!=null){
            example.setBegin(example.getBegin());
            example.setLength(example.getLength());
        }
        AdExample.Criteria criteria = example.createCriteria();
        if(dto.getAdId()!=null){
            criteria.andAdIdEqualTo(dto.getAdId());
        }
        return null;

    }

}
