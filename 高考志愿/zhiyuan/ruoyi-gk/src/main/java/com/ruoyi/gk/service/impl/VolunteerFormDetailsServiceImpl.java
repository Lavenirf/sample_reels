package com.ruoyi.gk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.gk.domain.VolunteerFormDetails;
import com.ruoyi.gk.domain.vo.VolunteerFormDetailsVo;
import com.ruoyi.gk.mapper.VolunteerFormDetailsMapper;
import com.ruoyi.gk.service.IVolunteerFormDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 志愿详情Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-16
 */
@Service
public class VolunteerFormDetailsServiceImpl implements IVolunteerFormDetailsService
{
    @Autowired
    private VolunteerFormDetailsMapper volunteerFormDetailsMapper;

    /**
     * 查询志愿详情
     *
     * @return 志愿详情
     */
    @Override
    public List<VolunteerFormDetailsVo> selectVolunteerFormDetailsByVoId(Integer voId,Integer paragraphId)
    {
        return volunteerFormDetailsMapper.selectVolunteerFormDetailsByVo(voId,paragraphId);
    }

    /**
     * 查询志愿详情列表
     * 
     * @param volunteerFormDetails 志愿详情
     * @return 志愿详情
     */
    @Override
    public List<VolunteerFormDetails> selectVolunteerFormDetailsList(VolunteerFormDetails volunteerFormDetails)
    {
        return volunteerFormDetailsMapper.selectVolunteerFormDetailsList(volunteerFormDetails);
    }

    /**
     * 新增志愿详情
     * 
     * @param volunteerFormDetails 志愿详情
     * @return 结果
     */
    @Override
    public int insertVolunteerFormDetails(VolunteerFormDetails volunteerFormDetails)
    {
        Long schoolId = volunteerFormDetails.getSchoolId();
        Long specialId = volunteerFormDetails.getSpecialId();
        //参数校验
        if (ObjectUtil.isNull(schoolId)&&ObjectUtil.isNull(specialId)){
            return 0;
        }else {
            //查询目前存在的志愿信息
            List<VolunteerFormDetails> volunteerFormList = volunteerFormDetailsMapper.selectVolunteerFormDetailsByVoId(volunteerFormDetails.getVolunteerFromId(),volunteerFormDetails.getParagraph());
            //判断当前新增的志愿是否已经存在
            List<VolunteerFormDetails> collect = volunteerFormList.stream().filter(vo -> Objects.equals(vo.getSpecialId(), specialId)).collect(Collectors.toList());
            long count = collect.stream().filter(vo -> Objects.equals(vo.getSchoolId(), schoolId)).count();
            if (count==0){
                int size = volunteerFormList.size();
                //判断志愿是否已满
                if (size==96){
                    return 2;
                }
                else if(volunteerFormList.isEmpty()){
                    volunteerFormDetails.setOrder(size+1);
                    volunteerFormDetailsMapper.insertVolunteerFormDetails(volunteerFormDetails);
                    return 1;
                }
                //判断志愿中是否有中间断层，有则插入中间断层
                else if(volunteerFormList.get(size-1).getOrder()>size){
                    volunteerFormDetails.setOrder(findFirstMissingOrder(volunteerFormList));
                    volunteerFormDetailsMapper.insertVolunteerFormDetails(volunteerFormDetails);
                    return 1;
                }
                //前面都不符合直接插入
                else {
                    volunteerFormDetails.setOrder(size+1);
                    volunteerFormDetailsMapper.insertVolunteerFormDetails(volunteerFormDetails);
                    return 1;
                }
            }
            return 3;
        }
    }

    /**
     * 修改志愿详情
     * 
     * @param volunteerFormDetails 志愿详情
     * @return 结果
     */
    @Override
    public int updateVolunteerFormDetails(VolunteerFormDetails volunteerFormDetails,Integer oldOrder)
    {
        Integer newOrder = volunteerFormDetails.getOrder();
        Long specialId = volunteerFormDetails.getSpecialId();
        Long schoolId = volunteerFormDetails.getSchoolId();
        if (ObjectUtil.isNull(newOrder)||ObjectUtil.isNull(schoolId)||ObjectUtil.isNull(specialId)){
            return 0;
        }else {
            if (oldOrder.equals(0)){
                volunteerFormDetailsMapper.updateVolunteerFormDetails(volunteerFormDetails);
            }else {
                VolunteerFormDetails vo = volunteerFormDetailsMapper.selectVolunteerFormDetailsByOrder(volunteerFormDetails.getOrder());
                if (ObjectUtil.isNull(vo)){
                    return 0;
                }else {
                    volunteerFormDetailsMapper.updateVolunteerFormDetails(volunteerFormDetails);
                    vo.setOrder(oldOrder);
                    volunteerFormDetailsMapper.updateVolunteerFormDetails(vo);
                }
            }
            return 1;
        }
    }

    /**
     * 删除志愿详情信息
     * 
     * @param id 志愿详情主键
     * @return 结果
     */
    @Override
    public int deleteVolunteerFormDetailsById(Long id)
    {
        return volunteerFormDetailsMapper.deleteVolunteerFormDetailsById(id);
    }
    /*
    * 断层查找
    * */
    public int findFirstMissingOrder(List<VolunteerFormDetails> list) {
        // 提取所有 order 字段并排序
        List<Integer> orders = new ArrayList<>();
        for (VolunteerFormDetails detail : list) {
            orders.add(detail.getOrder());
        }
        orders.sort(Integer::compareTo);

        // 找到缺失的最小 order 值
        int expectedOrder = 1;
        for (int order : orders) {
            if (order != expectedOrder) {
                return expectedOrder;
            }
            expectedOrder++;
        }
        return expectedOrder;
    }
}
