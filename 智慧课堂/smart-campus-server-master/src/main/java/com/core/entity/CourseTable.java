package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 课程表信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("课程表信息对象")
public class CourseTable extends BaseEntity {

    private Long id;

    private Long courseId;

    private Long clazzRoomId;

    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date courseDate;

    private Integer startSection;

    private Integer endSection;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private String courseName;

    @TableField(exist = false)
    private String courseImgUrl;

    @TableField(exist = false)
    private String clazzRoomName;

    /**
     * 开始时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 学年：2023
     */
    @TableField(exist = false)
    private int year;

    /**
     * 月： 10
     */
    @TableField(exist = false)
    private int month;

    /**
     * 日： 12
     */
    @TableField(exist = false)
    private int day;

    /**
     * 星期：1
     */
    @TableField(exist = false)
    private int weekDay;

}
