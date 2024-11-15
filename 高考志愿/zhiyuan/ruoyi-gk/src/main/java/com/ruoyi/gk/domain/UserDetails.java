package com.ruoyi.gk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户详情对象 user_details
 *
 * @author ruoyi
 * @date 2024-06-26
 */
public class UserDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long userId;

    /**
     * 高考地区
     */
    @Excel(name = "高考地区")
    private String examinationArea;

    /**
     * 生源地
     */
    @Excel(name = "生源地")
    private String birthplace;

    /**
     * 学校名
     */
    @Excel(name = "学校名")
    private String schoolName;

    /**
     * 身份信息
     */
    @Excel(name = "身份信息")
    private String identityInformation;

    /**
     * 年级
     */
    @Excel(name = "年级")
    private String grade;

    /**
     * 科目一
     */
    @Excel(name = "科目一")
    private String subject1;

    /**
     * 科目二
     */
    @Excel(name = "科目二")
    private String subject2;

    /**
     * 科目三
     */
    @Excel(name = "科目三")
    private String subject3;

    /**
     * 分数
     */
    @Excel(name = "分数")
    private Long fraction;

    /**
     * 位次
     */
    @Excel(name = "位次")
    private Long ranking;

    /**
     * 考生类型
     */
    @Excel(name = "考生类型")
    private String candidateType;
    /**
     * 志愿表id
     */
    @Excel(name = "志愿表id")
    private Long volunteerFormId;
    /**
     * 问卷id
     */
    @Excel(name = "问卷id")
    private Long questionnaireId;
    /**
     * 霍兰德测试结果id
     */
    @Excel(name = "霍兰德测试结果id")
    private Long hollandResultId;
    /**
     * 个人收藏id
     */
    @Excel(name = "个人收藏id")
    private String personalFavoritesId;
    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setExaminationArea(String examinationArea) {
        this.examinationArea = examinationArea;
    }

    public String getExaminationArea() {
        return examinationArea;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setIdentityInformation(String identityInformation) {
        this.identityInformation = identityInformation;
    }

    public String getIdentityInformation() {
        return identityInformation;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setFraction(Long fraction) {
        this.fraction = fraction;
    }

    public Long getFraction() {
        return fraction;
    }

    public void setRanking(Long ranking) {
        this.ranking = ranking;
    }

    public Long getRanking() {
        return ranking;
    }

    public void setCandidateType(String candidateType) {
        this.candidateType = candidateType;
    }

    public String getCandidateType() {
        return candidateType;
    }

    public Long getVolunteerFormId() {
        return volunteerFormId;
    }

    public void setVolunteerFormId(Long volunteerFormId) {
        this.volunteerFormId = volunteerFormId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Long getHollandResultId() {
        return hollandResultId;
    }

    public void setHollandResultId(Long hollandResultId) {
        this.hollandResultId = hollandResultId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("examinationArea", getExaminationArea())
                .append("birthplace", getBirthplace())
                .append("schoolName", getSchoolName())
                .append("identityInformation", getIdentityInformation())
                .append("grade", getGrade())
                .append("subject1", getSubject1())
                .append("subject2", getSubject2())
                .append("subject3", getSubject3())
                .append("fraction", getFraction())
                .append("ranking", getRanking())
                .append("candidateType", getCandidateType())
                .append("volunteerFormId", getVolunteerFormId())
                .append("questionnaireId", getQuestionnaireId())
                .append("hollandResultId", getHollandResultId())
                .append("remarks", getRemarks())
                .toString();
    }
}
