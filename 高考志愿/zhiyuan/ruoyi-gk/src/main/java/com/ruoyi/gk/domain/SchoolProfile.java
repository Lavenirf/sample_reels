package com.ruoyi.gk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;

/**
 * schoolProfile对象 school_profile
 * 
 * @author wbl
 * @date 2024-08-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolProfile {


    /** id */
    private Long id;

    /** 学校id */
    @Excel(name = "学校id")
    private Long schoolId;

    /** 学校名称 */
    @Excel(name = "学校名称")
    private String schoolName;

    /** 学校简介 */
    @Excel(name = "学校简介")
    private String schoolDetail;

    /** 住宿简介 */
    @Excel(name = "住宿简介")
    private String accommodation;

    /** 食堂简介 */
    @Excel(name = "食堂简介")
    private String diet;
}
