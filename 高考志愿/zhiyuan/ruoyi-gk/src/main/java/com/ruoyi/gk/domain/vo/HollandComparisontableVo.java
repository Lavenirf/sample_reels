package com.ruoyi.gk.domain.vo;

import com.ruoyi.gk.domain.HollandComparisontable;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class HollandComparisontableVo  {
    int R=0;
    int I=0;
    int A=0;
    int S=0;
    int E=0;
    int C=0;

    HollandComparisontable hollandComparisontable;
    int hollandResultId;
}
