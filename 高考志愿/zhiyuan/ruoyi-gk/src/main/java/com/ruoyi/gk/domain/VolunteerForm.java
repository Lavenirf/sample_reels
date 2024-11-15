package com.ruoyi.gk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

/**
 * 志愿对象 volunteer_form
 * 
 * @author ruoyi
 * @date 2024-07-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerForm {

    /** id */
    private Long id;

    /** 志愿表名称 */
    @Excel(name = "志愿表名称")
    private String name;
    /**
     * 使用成绩
     */
    private String result;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    private Date createTime;


}
