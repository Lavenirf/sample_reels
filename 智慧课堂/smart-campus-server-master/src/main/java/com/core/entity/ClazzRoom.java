package com.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 教室信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("教室信息对象")
public class ClazzRoom extends BaseEntity {

    private Long id;

    private Long num;

    private String name;

    private Double longitude;

    private Double latitude;

    private Long rowNum;

    private Long colNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
