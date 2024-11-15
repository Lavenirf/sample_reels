package com.ruoyi.gk.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolRankVo {
    private Integer id;
    private Integer schoolId;
    private String rank;
    private String rankType;
}
