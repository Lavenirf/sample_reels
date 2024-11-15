package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 教师发布考勤信息对象
 *
 * @author zph
 * @date 2023-07-17 12:35:57
 */
@Data
@ApiModel("教师发布考勤信息对象")
public class AttendPublish extends BaseEntity {

    private Long id;

    /**
     * 教师主键
     */
    @ApiModelProperty("教师主键")
    private Long teacherId;

    /**
     *  班级主键
     */
    @ApiModelProperty("班级主键")
    private String clazzIds;

    /**
     *  教室主键
     */
    @ApiModelProperty("教室主键")
    private Long clazzRoomId;

    /**
     *  发布日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("发布日期")
    private Date publishTime;

    /**
     *  开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     *  结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 教师名称
     */
    @TableField(exist = false)
    private String teacherName;

    /**
     * 班级名称
     */
    @TableField(exist = false)
    private String clazzNames;

    /**
     *  教室名称
     */
    @ApiModelProperty("教室名称")
    @TableField(exist = false)
    private String clazzRoomName;

    /**
     *  教室
     */
    @ApiModelProperty("教室")
    @TableField(exist = false)
    private ClazzRoom clazzRoom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
