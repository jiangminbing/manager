package com.soft.manager.service;

import com.soft.manager.dao.AdMapper;
import com.soft.manager.po.Ad;
import com.soft.manager.po.AdExample;
import com.soft.parent.basic.req.AdSearchDto;
import com.soft.parent.basic.res.AdDto;
import com.soft.parent.basic.result.PageResult;
import com.soft.parent.basic.result.ResCode;
import com.soft.parent.basic.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public PageResult<AdDto>  getAdByPage(AdSearchDto dto) throws Exception{
        AdExample adExample = createCriteria(dto);
        long total = adMapper.countByExample(adExample);
        if(total>0){
            List<Ad> list = adMapper.selectByExample(adExample);
            List<AdDto> resList = new ArrayList<>();
            for(Ad ad:list){
                AdDto adDto = new AdDto();
                BeanUtils.copyProperties(ad,adDto);
                resList.add(adDto);
            }
            PageResult<AdDto> pageResult = new PageResult<>(ResCode.SUCCESS);
            pageResult.setData(resList);
            pageResult.setTotal(total);
            return pageResult;
        }

        return new PageResult<AdDto>(ResCode.NO_DATA);
    }

    /**
     * 通过Id查询
     * @param id
     * @return
     * @throws Exception
     */
    public AdDto getAdById(Integer id) throws Exception{
        Ad ad = adMapper.selectByPrimaryKey(id);
        AdDto adDto = new AdDto();
        BeanUtils.copyProperties(ad,adDto);
        return  adDto;
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
