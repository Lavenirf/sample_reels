package com.ruoyi.gk.mapper;

import java.util.List;
import com.ruoyi.gk.domain.Colleges;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * 推荐院校Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-18
 */
public interface CollegesMapper
{
    /**
     * 查询推荐院校
     *
     * @param id 推荐院校主键
     * @return 推荐院校
     */
    public Colleges selectCollegesById(Long id);

    /**
     * 查询推荐院校列表
     *
     * @param colleges 推荐院校
     * @return 推荐院校集合
     */
    public List<Colleges> selectCollegesList(Colleges colleges);

    /**
     * 新增推荐院校
     *
     * @param colleges 推荐院校
     * @return 结果
     */
    public int insertColleges(Colleges colleges);

    /**
     * 修改推荐院校
     *
     * @param colleges 推荐院校
     * @return 结果
     */
    public int updateColleges(Colleges colleges);

    /**
     * 删除推荐院校
     *
     * @param id 推荐院校主键
     * @return 结果
     */
    public int deleteCollegesById(Long id);

    /**
     * 批量删除推荐院校
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCollegesByIds(Long[] ids);

    @Delete("delete  from recommended_institutions where userId = #{userId}")
    public int delectCollegesByuserId(@Param("userId") Long userId);
}
