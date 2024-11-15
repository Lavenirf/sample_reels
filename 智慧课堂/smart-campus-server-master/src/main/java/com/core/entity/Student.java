package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 学员信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("学员信息对象")
public class Student extends BaseEntity {

    private Long id;

    private Long userId;

    private Long clazzId;

    private Long num;

    private String name;

    private String gender;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private String clazzIds;

}
