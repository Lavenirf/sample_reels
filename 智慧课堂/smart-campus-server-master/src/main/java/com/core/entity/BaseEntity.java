package com.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
class BaseEntity implements Serializable {

    @TableField(exist = false)
    private Integer page;

    @TableField(exist = false)
    private Integer pageSize;

}
