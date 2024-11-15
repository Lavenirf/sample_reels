package com.ruoyi.gk.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerFormDetailsExportVo {
    private Long id;
    private String schoolName;
    private String natureName;
    private Integer codeEnroll;
    private String specialName;
    private String typeName;
    private String limitYear;
    private Integer paragraph;
    private Integer order;
}
