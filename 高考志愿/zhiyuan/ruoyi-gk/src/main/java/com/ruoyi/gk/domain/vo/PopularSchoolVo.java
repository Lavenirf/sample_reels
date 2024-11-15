package com.ruoyi.gk.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PopularSchoolVo {
    private Integer id;
    private String name;
    private Integer schoolId;
    private String natureName;
    private String typeName;
    private Integer codeEnroll;
    private String viewTotalNumber;
    private String provinceName;
}
