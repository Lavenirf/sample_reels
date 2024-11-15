package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 考勤信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("考勤信息对象")
public class Attend extends BaseEntity {

    private Long id;

    private Long studentId;

    private Long attendPublishId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date clockTime;

    private String attendStatus;

    private Double longitude;

    private Double latitude;

    private Double distance;

    @TableField(exist = false)
    private AttendPublish attendPublish;

    @TableField(exist = false)
    private ClazzRoomSeat clazzRoomSeat;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
