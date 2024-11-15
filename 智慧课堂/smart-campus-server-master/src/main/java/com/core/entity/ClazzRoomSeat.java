package com.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 教室座位信息对象
 *
 * @author zjp
 * @date 2023-07-25 20:16:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("教室座位信息对象")
public class ClazzRoomSeat extends BaseEntity {

    /**
     *  主键
     */
    private Long id;

    /**
     *  教室主键
     */
    @ApiModelProperty("教室主键")
    private Long clazzRoomId;

    /**
     *  座位编号
     */
    @ApiModelProperty("座位编号")
    private Long num;

    /**
     *  座位经度
     */
    @ApiModelProperty("座位经度")
    private Double longitude;

    /**
     *  座位纬度
     */
    @ApiModelProperty("座位纬度")
    private Double latitude;

    /**
     *  所在教室行
     */
    @ApiModelProperty("所在教室行")
    private Long rowNum;

    /**
     *  所在教师列
     */
    @ApiModelProperty("所在教师列")
    private Long colNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
