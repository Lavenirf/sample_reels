package com.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 课程信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("课程信息对象")
public class Course extends BaseEntity {

    private Long id;

    private Long num;

    private String name;

    private Integer score;

    private String imgUrl;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
