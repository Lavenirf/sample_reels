package com.ruoyi.gk.domain.vo;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolFractionVo {
    /** 主键 */
    private Long id;
    /** 所属地区名称 */
    @Excel(name = "所属地区名称")
    private String cityName;
    /** 院校代码 */
    @Excel(name = "院校代码")
    private String codeEnroll;
    /** 具体区域 */
    @Excel(name = "具体区域")
    private String countyName;
    /** 学校特色 */
    @Excel(name = "学校特色")
    private String dualClassName;
    /** 几段 */
    @Excel(name = "几段")
    private String batchName;
    /** 类 */
    @Excel(name = "类")
    private String zsName;
    /** 学校名称 */
    @Excel(name = "学校名称")
    private String name;
    /** 院校类型 */
    @Excel(name = "院校类型")
    private String typeName;
    private String levelName;
    @Excel(name = "最低分")
    private Integer min;
    @Excel(name = "最低位次")
    private Integer minSection;
    private Integer schoolId;
    private Integer f985;
    private Integer f211;
}
