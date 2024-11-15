package com.ruoyi.gk.service;

import java.util.List;
import com.ruoyi.gk.domain.EnrollmentPlan;
import com.ruoyi.gk.domain.vo.EnrollmentPlanVo;

/**
 * enrollmentPlanService接口
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
public interface IEnrollmentPlanService 
{
    /**
     * 查询enrollmentPlan
     * 
     * @param schoolId enrollmentPlan主键
     * @return enrollmentPlan
     */
    public EnrollmentPlan selectEnrollmentPlanBySchoolId(String schoolId);

    /**
     * 查询enrollmentPlan列表
     * 
     * @param enrollmentPlan enrollmentPlan
     * @return enrollmentPlan集合
     */
    public List<EnrollmentPlan> selectEnrollmentPlanList(EnrollmentPlan enrollmentPlan);

    /**
     * 查询enrollmentPlan差距列表
     *
     * @param enrollmentPlan enrollmentPlan
     * @return enrollmentPlan集合
     */
    public List<List<EnrollmentPlanVo>> selectEnrollmentPlanGapList(List<EnrollmentPlanVo> enrollmentPlan);

    /**
     * 新增enrollmentPlan
     * 
     * @param enrollmentPlan enrollmentPlan
     * @return 结果
     */
    public int insertEnrollmentPlan(EnrollmentPlan enrollmentPlan);

    /**
     * 修改enrollmentPlan
     * 
     * @param enrollmentPlan enrollmentPlan
     * @return 结果
     */
    public int updateEnrollmentPlan(EnrollmentPlan enrollmentPlan);

    /**
     * 批量删除enrollmentPlan
     * 
     * @param schoolIds 需要删除的enrollmentPlan主键集合
     * @return 结果
     */
    public int deleteEnrollmentPlanBySchoolIds(String[] schoolIds);

    /**
     * 删除enrollmentPlan信息
     * 
     * @param schoolId enrollmentPlan主键
     * @return 结果
     */
    public int deleteEnrollmentPlanBySchoolId(String schoolId);
}
