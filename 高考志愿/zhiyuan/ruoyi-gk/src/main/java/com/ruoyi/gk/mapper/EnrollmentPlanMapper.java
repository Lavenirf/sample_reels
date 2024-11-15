package com.ruoyi.gk.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.gk.domain.EnrollmentPlan;
import com.ruoyi.gk.domain.vo.EnrollmentPlanVo;

/**
 * enrollmentPlanMapper接口
 *
 * @author ruoyi
 * @date 2024-08-23
 */
public interface EnrollmentPlanMapper
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

    public List<EnrollmentPlanVo> selectEnrollmentPlanGapList(EnrollmentPlanVo enrollmentPlan);
    public List<EnrollmentPlanVo> selectEnrollmentPlanGapListBatch(Map<String, Object> map);
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
     * 删除enrollmentPlan
     *
     * @param schoolId enrollmentPlan主键
     * @return 结果
     */
    public int deleteEnrollmentPlanBySchoolId(String schoolId);

    /**
     * 批量删除enrollmentPlan
     *
     * @param schoolIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnrollmentPlanBySchoolIds(String[] schoolIds);
}
