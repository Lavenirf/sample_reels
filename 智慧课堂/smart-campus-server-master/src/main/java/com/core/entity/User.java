package com.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 账户表信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("账户表信息对象")
public class User extends BaseEntity {

    private Long id;

    private String username;

    private String password;

    private String type;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
