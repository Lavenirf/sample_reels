package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 作业信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("作业信息对象")
public class Homework extends BaseEntity {

    private Long id;

    private String clazzIds;

    private Long teacherId;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    private String fileUrl;

    private String content;

    private Long courseId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private Long totalStudent;

    @TableField(exist = false)
    private Long count;

    @TableField(exist = false)
    private String clazzNames;

    @TableField(exist = false)
    private String teacherName;

    @TableField(exist = false)
    private String courseName;

}
