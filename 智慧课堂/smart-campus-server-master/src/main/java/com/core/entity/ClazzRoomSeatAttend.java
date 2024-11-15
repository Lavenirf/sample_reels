package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 学员考勤座位信息对象
 *
 * @author zjp
 * @date 2023-07-25 20:17:00
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("学员考勤座位信息对象")
@Data
public class ClazzRoomSeatAttend extends BaseEntity {

    private Long id;

    private Long clazzRoomId;

    private Long clazzRoomSeatId;

    private Long studentId;

    private Long publishAttendId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private String studentName;

    @TableField(exist = false)
    private ClazzRoomSeat clazzRoomSeat;
}
