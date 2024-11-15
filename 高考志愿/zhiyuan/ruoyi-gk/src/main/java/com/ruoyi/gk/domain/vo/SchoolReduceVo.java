package com.ruoyi.gk.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolReduceVo {
    private Long id;

    private Long schoolId;


    /**
     * 所属地区名称
     */
    private String cityName;

    /** 省份名称 */
    @Excel(name = "省份名称")
    private String provinceName;

    /** 办学性质 */
    @Excel(name = "办学性质")
    private String natureName;
    /**
     * 办学类型
     */
    private String levelName;

    /**
     * 学校名称
     */
    private String name;
    /**
     * 院校类型
     */
    private String typeName;
}
