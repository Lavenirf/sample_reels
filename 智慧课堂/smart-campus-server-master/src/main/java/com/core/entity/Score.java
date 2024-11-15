package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 成绩信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("成绩信息对象")
public class Score extends BaseEntity {

    private Long id;

    private Long clazzId;

    private Long studentId;

    private Long examId;

    private Long score;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private Exam exam;

    @TableField(exist = false)
    private String studentName;

    @TableField(exist = false)
    private Long studentNum;

}
