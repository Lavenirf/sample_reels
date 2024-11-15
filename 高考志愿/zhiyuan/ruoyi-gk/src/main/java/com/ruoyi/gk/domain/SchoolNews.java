package com.ruoyi.gk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;


/**
 * news对象 school_news
 * 
 * @author wbl
 * @date 2024-08-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolNews {


    /** id */
    private Long id;

    /** 新闻名称 */
    @Excel(name = "新闻名称")
    private String newsTitle;

    /** 新闻内容 */
    @Excel(name = "新闻内容")
    private String newsContent;

    /** 发布单位 */
    @Excel(name = "发布单位")
    private String publishPlace;

    /** 发布时间 */
    @Excel(name = "发布时间")
    private String publishDate;

    /** 地址 */
    @Excel(name = "地址")
    private String newsUrl;
}
