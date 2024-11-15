package com.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 教师班级信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:39
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("教师班级信息对象")
public class TeacherClazz extends BaseEntity {

    private Long id;

    private Long teacherId;

    private Long clazzId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
