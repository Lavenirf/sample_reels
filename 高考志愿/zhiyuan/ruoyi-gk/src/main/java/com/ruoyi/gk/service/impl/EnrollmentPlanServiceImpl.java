package com.ruoyi.gk.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gk.domain.vo.EnrollmentPlanVo;
import com.ruoyi.gk.domain.vo.UserDetailsVo;
import com.ruoyi.gk.service.IUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.gk.mapper.EnrollmentPlanMapper;
import com.ruoyi.gk.domain.EnrollmentPlan;
import com.ruoyi.gk.service.IEnrollmentPlanService;

/**
 * enrollmentPlanService业务层处理
 *
 * @author ruoyi
 * @date 2024-08-23
 */
@Service
public class EnrollmentPlanServiceImpl implements IEnrollmentPlanService
{
    @Autowired
    private EnrollmentPlanMapper enrollmentPlanMapper;
    @Autowired
    private IUserDetailsService userDetailsService;

    /**
     * 查询enrollmentPlan
     *
     * @param schoolId enrollmentPlan主键
     * @return enrollmentPlan
     */
    @Override
    public EnrollmentPlan selectEnrollmentPlanBySchoolId(String schoolId)
    {
        return enrollmentPlanMapper.selectEnrollmentPlanBySchoolId(schoolId);
    }

    /**
     * 查询enrollmentPlan列表
     *
     * @param enrollmentPlan enrollmentPlan
     * @return enrollmentPlan
     */
    @Override
    public List<EnrollmentPlan> selectEnrollmentPlanList(EnrollmentPlan enrollmentPlan)
    {
        String area = enrollmentPlan.getArea();
        // 创建HashMap映射省份代码到省份名称
        Map<String, String> provinceMap = new HashMap<>();
        // 添加所有省、自治区、直辖市和特别行政区的代码和对应的名称
        provinceMap.put( "北京","11");
        provinceMap.put( "天津","12");
        provinceMap.put( "河北","13");
        provinceMap.put( "山西","14");
        provinceMap.put( "内蒙古","15");
        provinceMap.put( "辽宁","21");
        provinceMap.put( "吉林","22");
        provinceMap.put( "黑龙江","23");
        provinceMap.put( "上海","31");
        provinceMap.put( "江苏","32");
        provinceMap.put( "浙江","33");
        provinceMap.put( "安徽","34");
        provinceMap.put( "福建","35");
        provinceMap.put( "江西","36");
        provinceMap.put( "山东","37");
        provinceMap.put( "河南","41");
        provinceMap.put( "湖北","42");
        provinceMap.put( "湖南","43");
        provinceMap.put( "广东","44");
        provinceMap.put( "广西","45");
        provinceMap.put( "海南","46");
        provinceMap.put( "重庆","50");
        provinceMap.put( "四川","51");
        provinceMap.put( "贵州","52");
        provinceMap.put( "云南","53");
        provinceMap.put( "西藏","54");
        provinceMap.put( "陕西","61");
        provinceMap.put( "甘肃","62");
        provinceMap.put( "青海","63");
        provinceMap.put( "宁夏","64");
        provinceMap.put( "新疆","65");
        provinceMap.put( "台湾","71");
        provinceMap.put( "香港","81");
        provinceMap.put( "澳门","82");
        String s = provinceMap.get(area);
        enrollmentPlan.setArea(s);
        return enrollmentPlanMapper.selectEnrollmentPlanList(enrollmentPlan);
    }

    /**
     * 查询enrollmentPlan差距列表
     *
     * @param enrollmentPlan enrollmentPlan
     * @return enrollmentPlan
     */
    @Override
    public List<List<EnrollmentPlanVo>> selectEnrollmentPlanGapList(List<EnrollmentPlanVo> enrollmentPlan) {

        SysUser user = SecurityUtils.getLoginUser().getUser();
        UserDetailsVo userDetailsVo = userDetailsService.selectUserDetailsByUserId(user.getUserId());

        // 获取批量查询所需的参数列表
        List<String> specialIds = enrollmentPlan.stream().map(EnrollmentPlanVo::getSpecialId).collect(Collectors.toList());
        List<String> spnames = enrollmentPlan.stream().map(EnrollmentPlanVo::getSpname).collect(Collectors.toList());
        List<String> years = enrollmentPlan.stream().map(EnrollmentPlanVo::getYear).collect(Collectors.toList());

        // 传递到Map中
        Map<String, Object> params = new HashMap<>();
        params.put("specialIds", specialIds);
        params.put("localProvinceName", userDetailsVo.getExaminationArea());
        params.put("spnames", spnames);
        if (years != null && years.size() > 0&& years.get(0)!=null) {
            params.put("years", years);
        }
        // 一次性查询所有结果
        List<EnrollmentPlanVo> result = enrollmentPlanMapper.selectEnrollmentPlanGapListBatch(params);
        // 计算每个项的 gap 值
        result.forEach(v -> v.setGap(v.getMinSection() - userDetailsVo.getRanking()));

        Map<String, List<EnrollmentPlanVo>> groupedBySpname = result.stream()
                .collect(Collectors.groupingBy(EnrollmentPlanVo::getSpname));

        ArrayList<List<EnrollmentPlanVo>> vo = new ArrayList<>(groupedBySpname.values());
        return vo;
    }


    /**
     * 新增enrollmentPlan
     *
     * @param enrollmentPlan enrollmentPlan
     * @return 结果
     */
    @Override
    public int insertEnrollmentPlan(EnrollmentPlan enrollmentPlan)
    {
        return enrollmentPlanMapper.insertEnrollmentPlan(enrollmentPlan);
    }

    /**
     * 修改enrollmentPlan
     *
     * @param enrollmentPlan enrollmentPlan
     * @return 结果
     */
    @Override
    public int updateEnrollmentPlan(EnrollmentPlan enrollmentPlan)
    {
        return enrollmentPlanMapper.updateEnrollmentPlan(enrollmentPlan);
    }

    /**
     * 批量删除enrollmentPlan
     *
     * @param schoolIds 需要删除的enrollmentPlan主键
     * @return 结果
     */
    @Override
    public int deleteEnrollmentPlanBySchoolIds(String[] schoolIds)
    {
        return enrollmentPlanMapper.deleteEnrollmentPlanBySchoolIds(schoolIds);
    }

    /**
     * 删除enrollmentPlan信息
     *
     * @param schoolId enrollmentPlan主键
     * @return 结果
     */
    @Override
    public int deleteEnrollmentPlanBySchoolId(String schoolId)
    {
        return enrollmentPlanMapper.deleteEnrollmentPlanBySchoolId(schoolId);
    }
}
