package com.ruoyi.gk.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.gk.domain.PersonalFavorites;
import com.ruoyi.gk.domain.Schools;
import com.ruoyi.gk.domain.SpecialDetails;
import com.ruoyi.gk.domain.vo.CollectVo;
import com.ruoyi.gk.domain.vo.SchoolReduceVo;
import com.ruoyi.gk.domain.vo.SpecialReduceVo;
import com.ruoyi.gk.mapper.PersonalFavoritesMapper;
import com.ruoyi.gk.service.IPersonalFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 个人收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-01
 */
@Service
public class PersonalFavoritesServiceImpl implements IPersonalFavoritesService
{
    @Autowired
    private PersonalFavoritesMapper personalFavoritesMapper;

    /**
     * 查询个人收藏
     * 
     * @param id 个人收藏主键
     * @return 个人收藏
     */
    @Override
    public CollectVo selectPersonalFavoritesById(Long id)
    {
        CollectVo collectVo = new CollectVo();
        List<SchoolReduceVo> schoolReduceVoList =new ArrayList<>();
        List<SpecialReduceVo> specialReduceVoList =new ArrayList<>();
        List<PersonalFavorites> personalFavorites = personalFavoritesMapper.selectPersonalFavoritesById(id);
        for (PersonalFavorites p:personalFavorites){
            if (p.getCollectType()==0){
                Schools schools = personalFavoritesMapper.selectSchoolById(p.getSchoolId());
                SchoolReduceVo schoolReduceVo = new SchoolReduceVo();
                BeanUtil.copyProperties(schools,schoolReduceVo);
                schoolReduceVo.setId(p.getId());
                schoolReduceVo.setSchoolId(p.getSchoolId());
                schoolReduceVoList.add(schoolReduceVo);
            }else {
                SpecialDetails specialDetails =personalFavoritesMapper.selectSpecialById(p.getSpecialId());
                System.out.println(specialDetails);
                SpecialReduceVo specialReduceVo = new SpecialReduceVo();
                BeanUtil.copyProperties(specialDetails,specialReduceVo);
                specialReduceVo.setId(p.getId());
                specialReduceVo.setSpecialId(p.getSpecialId());
                specialReduceVoList.add(specialReduceVo);
            }
        }
        collectVo.setSchoolReduceVoList(schoolReduceVoList);
        collectVo.setSpecialReduceVoList(specialReduceVoList);
        return collectVo;
    }


    /**
     * 新增个人收藏
     * 
     * @param personalFavorites 个人收藏
     * @return 结果
     */
    @Override
    public int insertPersonalFavorites(PersonalFavorites personalFavorites)
    {
        return personalFavoritesMapper.insertPersonalFavorites(personalFavorites);
    }



    /**
     * 删除个人收藏信息
     * 
     * @param id 个人收藏主键
     * @return 结果
     */
    @Override
    public int deletePersonalFavoritesById(Long id)
    {
        return personalFavoritesMapper.deletePersonalFavoritesById(id);
    }
}
