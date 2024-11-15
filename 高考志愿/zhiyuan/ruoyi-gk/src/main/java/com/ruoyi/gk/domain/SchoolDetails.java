package com.ruoyi.gk.domain;

import com.ruoyi.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学校详情对象 school_details
 * 
 * @author ruoyi
 * @date 2024-08-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDetails {


    /** 主键id */
    private Long id;

    /** 学校id */
    @Excel(name = "学校id")
    private String schoolId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;
    /** 学科数量 */
    @Excel(name = "学科数量")
    private String numSubject;

    /** 硕士数量 */
    @Excel(name = "硕士数量")
    private String numMaster;

    /** 博士数量 */
    @Excel(name = "博士数量")
    private String numDoctor;

    /** 院士数量 */
    @Excel(name = "院士数量")
    private String numAcademician;

    /** 图书馆数量 */
    @Excel(name = "图书馆数量")
    private String numLibrary;

    /** 实验室数量 */
    @Excel(name = "实验室数量")
    private String numLab;

    /** 省ID */
    @Excel(name = "省ID")
    private String provinceId;

    /** 市ID */
    @Excel(name = "市ID")
    private String cityId;

    /** 是否广告 */
    @Excel(name = "是否广告")
    private String isAds;

    /** 是否招聘 */
    @Excel(name = "是否招聘")
    private String isRecruitment;

    /** 创建日期 */
    @Excel(name = "创建日期")
    private String createDate;

    /** 区域 */
    @Excel(name = "区域")
    private String area;

    /** 旧名 */
    @Excel(name = "旧名")
    private String oldName;

    /** 是否分校 */
    @Excel(name = "是否分校")
    private String isFenxiao;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 简称 */
    @Excel(name = "简称")
    private String shortName;

    /** 美国排名 */
    @Excel(name = "美国排名")
    private String usRank;

    /** 是否有Logo */
    @Excel(name = "是否有Logo")
    private String isLogo;

    /** 硕士数量2 */
    @Excel(name = "硕士数量2")
    private String numMaster2;

    /** 博士数量2 */
    @Excel(name = "博士数量2")
    private String numDoctor2;

    /** AI状态 */
    @Excel(name = "AI状态")
    private String aiStatus;

    /** 是否广告2 */
    @Excel(name = "是否广告2")
    private String isAds2;

    /** 合作资金 */
    @Excel(name = "合作资金")
    private String coopMoney;

    /** GBH数量 */
    @Excel(name = "GBH数量")
    private String gbhNum;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 学校邮箱 */
    @Excel(name = "学校邮箱")
    private String schoolEmail;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String postcode;

    /** 网站 */
    @Excel(name = "网站")
    private String site;

    /** 学校网站 */
    @Excel(name = "学校网站")
    private String schoolSite;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 内容 */
    @Excel(name = "内容")
    private String content;
}
