package com.ruoyi.gk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;

/**
 * 个人收藏对象 personal_favorites
 * 
 * @author ruoyi
 * @date 2024-07-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalFavorites {
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 收藏类型 0为院校 1为专业 */
    @Excel(name = "收藏类型 0为院校 1为专业")
    private Long collectType;

    /** 院校id */
    @Excel(name = "院校id")
    private Long schoolId;

    /** 专业id */
    @Excel(name = "专业id")
    private Long specialId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 备注 */
    @Excel(name = "备注")
    private String remake;

}
