package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 学生作业信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("学生作业信息对象")
public class StudentHomework extends BaseEntity {

    private Long id;

    private Long studentId;

    private Long homeworkId;

    private String fileUrl;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date uploadTime;

    private Integer score;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private Homework homework;

    @TableField(exist = false)
    private String studentName;

}
