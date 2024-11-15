package com.ruoyi.gk.mapper;

import com.ruoyi.gk.domain.PersonalFavorites;
import com.ruoyi.gk.domain.Schools;
import com.ruoyi.gk.domain.SpecialDetails;

import java.util.List;


/**
 * 个人收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-01
 */
public interface PersonalFavoritesMapper 
{
    /**
     * 查询个人收藏
     * 
     * @param id 个人收藏主键
     * @return 个人收藏
     */
    public List<PersonalFavorites> selectPersonalFavoritesById(Long id);

    /**
     * 新增个人收藏
     * 
     * @param personalFavorites 个人收藏
     * @return 结果
     */
    public int insertPersonalFavorites(PersonalFavorites personalFavorites);

    /**
     * 删除个人收藏
     * 
     * @param id 个人收藏主键
     * @return 结果
     */
    public int deletePersonalFavoritesById(Long id);

    /**
     * 查询个人收藏学校
     *
     *
     * @return 结果
     */
    Schools selectSchoolById(Long schoolId);

    SpecialDetails selectSpecialById(Long specialId);
}
