package com.ruoyi.gk.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectVo {
    List<SchoolReduceVo> schoolReduceVoList;
    List<SpecialReduceVo> specialReduceVoList;
}
