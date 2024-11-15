package com.ruoyi.gk.service;

import com.ruoyi.gk.domain.PersonalFavorites;
import com.ruoyi.gk.domain.vo.CollectVo;



/**
 * 个人收藏Service接口
 * 
 * @author ruoyi
 * @date 2024-07-01
 */
public interface IPersonalFavoritesService 
{
    /**
     * 查询个人收藏
     * 
     * @param id 个人收藏主键
     * @return 个人收藏
     */
    public CollectVo selectPersonalFavoritesById(Long id);

    /**
     * 新增个人收藏
     * 
     * @param personalFavorites 个人收藏
     * @return 结果
     */
    public int insertPersonalFavorites(PersonalFavorites personalFavorites);

    /**
     * 删除个人收藏信息
     * 
     * @param id 个人收藏主键
     * @return 结果
     */
    public int deletePersonalFavoritesById(Long id);
}
