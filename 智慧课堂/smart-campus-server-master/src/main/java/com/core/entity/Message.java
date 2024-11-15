package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 通知消息信息对象
 *
 * @author zph
 * @date 2023-07-14 19:35:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("通知消息信息对象")
public class Message extends BaseEntity {

    private Long id;

    private Long userId;

    private String type;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date sendTime;

    private Long bussId;

    private String subType;

    private String isRead;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private String clazzIds;
}
