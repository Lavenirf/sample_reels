package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 考试信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("考试信息对象")
public class Exam extends BaseEntity {

    private Long id;

    private Long courseId;

    private Long teacherId;

    private String clazzIds;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date examTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private String teacherName;

    @TableField(exist = false)
    private String clazzNames;

    @TableField(exist = false)
    private Long score;

    @TableField(exist = false)
    private Course course;
}
