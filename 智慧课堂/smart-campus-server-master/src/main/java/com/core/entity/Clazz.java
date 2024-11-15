package com.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 班级信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("班级信息对象")
public class Clazz extends BaseEntity {

    private Long id;

    private Long num;

    private String name;

    private String grade;

    private Integer rowNum;

    private Integer colNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
