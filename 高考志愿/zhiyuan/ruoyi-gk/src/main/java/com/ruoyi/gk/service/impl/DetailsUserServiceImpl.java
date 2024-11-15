package com.ruoyi.gk.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.gk.domain.*;
import com.ruoyi.gk.domain.vo.SchoolReduceVo;
import com.ruoyi.gk.domain.vo.SpecialReduceVo;
import com.ruoyi.gk.domain.vo.UserDetailsVo;
import com.ruoyi.gk.mapper.SchoolsMapper;
import com.ruoyi.gk.mapper.SpecialAllDetailsMapper;
import com.ruoyi.gk.mapper.UserDetailsMapper;
import com.ruoyi.gk.service.IUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
@Service
public class DetailsUserServiceImpl implements IUserDetailsService
{
    @Autowired
    private UserDetailsMapper userDetailsMapper;
    @Autowired
    private SpecialAllDetailsMapper specialAllDetailsMapper;
    @Autowired
    private SchoolsMapper schoolsMapper;

    /**
     * 查询用户详情
     * 
     * @param userId 用户详情主键
     * @return 用户详情
     */
    @Override
    public UserDetailsVo selectUserDetailsByUserId(Long userId)
    {
        UserDetailsVo userDetailsVo = new UserDetailsVo();
        UserInformation userDetails = userDetailsMapper.selectUserDetailsByUserId(userId);
        List<SchoolReduceVo> schoolsList = new ArrayList<>();
        List<SpecialReduceVo> specialList = new ArrayList<>();
        List<PersonalFavorites> personalFavoritesList = userDetails.getPersonalFavoritesList();
        for (PersonalFavorites p : personalFavoritesList){
            if (p.getCollectType()==0){
                //查询院校信息
                if (p.getSchoolId()!=null){
                    Schools schools = schoolsMapper.selectSchoolsById(p.getSchoolId());
                    SchoolReduceVo schoolReduceVo = new SchoolReduceVo();
                    BeanUtil.copyProperties(schools,schoolReduceVo);
                    schoolReduceVo.setId(p.getId());
                    schoolReduceVo.setSchoolId(p.getSchoolId());
                    schoolsList.add(schoolReduceVo);
                }
            }else {
                //查询专业信息
                if (p.getSpecialId()!=null){
                    SpecialDetails specialDetails = specialAllDetailsMapper.selectSpecialAllDetailsById(p.getSpecialId());
                    SpecialReduceVo specialReduceVo = new SpecialReduceVo();
                    BeanUtil.copyProperties(specialDetails,specialReduceVo);
                    specialReduceVo.setId(p.getId());
                    specialReduceVo.setSpecialId(p.getSpecialId());
                    specialList.add(specialReduceVo);
                }
            }
        }
        userDetailsVo.setSchoolsCollectList(schoolsList);
        userDetailsVo.setSpecialCollectList(specialList);
        BeanUtil.copyProperties(userDetails, userDetailsVo);
        return userDetailsVo;
    }

    /**
     * 修改用户详情
     * 
     * @param userInformation 用户详情
     * @return 结果
     */
    @Override
    public int updateUserDetails(UserInformation userInformation)
    {
        return userDetailsMapper.updateUserDetails(userInformation);
    }


}
