package com.ruoyi.gk.mapper;

import com.ruoyi.gk.domain.Schools;
import com.ruoyi.gk.domain.vo.PopularSchoolVo;
import com.ruoyi.gk.domain.vo.SchoolFractionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 学校Mapper接口
 *
 * @author ruoyi
 * @date 2024-07-02
 */
public interface SchoolsMapper {
    /**
     * 查询学校
     *
     * @param id 学校主键
     * @return 学校
     */
    public Schools selectSchoolsById(Long id);

    /**
     * 查询学校列表
     *
     * @param schools 学校
     * @return 学校集合
     */
    public List<Schools> selectSchoolsList(Schools schools);

    /**
     * 新增学校
     *
     * @param schools 学校
     * @return 结果
     */
    public int insertSchools(Schools schools);

    /**
     * 修改学校
     *
     * @param schools 学校
     * @return 结果
     */
    public int updateSchools(Schools schools);

    /**
     * 删除学校
     *
     * @param id 学校主键
     * @return 结果
     */
    public int deleteSchoolsById(Long id);

    /**
     * 批量删除学校
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSchoolsByIds(Long[] ids);

    /**
     * 查询学校列表by地区
     *
     * @return 学校集合
     */
    List<SchoolFractionVo> selectSchoolsListByLocal(@Param("localName") String localName, @Param("typeName") String typeName,
                                                    @Param("levelName") String levelName, @Param("name") String name,@Param("year") Integer year,@Param("provinceName") String provinceName);

    List<PopularSchoolVo> getPopularSchool(String local);

    List<PopularSchoolVo> selectSchoolListByName(String name);

    List<PopularSchoolVo> selectSchoolByIds(List<Integer> ids);
}
