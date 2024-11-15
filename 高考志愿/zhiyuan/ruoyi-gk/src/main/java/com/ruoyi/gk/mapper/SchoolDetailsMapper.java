package com.ruoyi.gk.mapper;

import java.util.List;
import com.ruoyi.gk.domain.SchoolDetails;
import com.ruoyi.gk.domain.vo.SchoolDetailsVo;

/**
 * 学校详情Mapper接口
 * 
 * @author ruoyi
 * @date 2024-08-03
 */
public interface SchoolDetailsMapper 
{
    /**
     * 查询学校详情
     * 
     * @param id 学校详情主键
     * @return 学校详情
     */
    public SchoolDetailsVo selectSchoolDetailsById(Long id);

    /**
     * 查询学校详情列表
     * 
     * @param schoolDetails 学校详情
     * @return 学校详情集合
     */
    public List<SchoolDetails> selectSchoolDetailsList(SchoolDetails schoolDetails);

    /**
     * 新增学校详情
     * 
     * @param schoolDetails 学校详情
     * @return 结果
     */
    public int insertSchoolDetails(SchoolDetails schoolDetails);

    /**
     * 修改学校详情
     * 
     * @param schoolDetails 学校详情
     * @return 结果
     */
    public int updateSchoolDetails(SchoolDetails schoolDetails);

    /**
     * 删除学校详情
     * 
     * @param id 学校详情主键
     * @return 结果
     */
    public int deleteSchoolDetailsById(Long id);

    /**
     * 批量删除学校详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolDetailsByIds(Long[] ids);
}
