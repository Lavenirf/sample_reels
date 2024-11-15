package com.ruoyi.gk.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HollandUserComparisontableVo {
    private Integer id;
    private double r;
    private double i;
    private double a;
    private double c;
    private double e;
    private double s;
    private String comparisontableMorphology;
    private String personality1;
    private String personality2;
    private String personality3;
    private String typicalProfession;
    private Long userId;
}

