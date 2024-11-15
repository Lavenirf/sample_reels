package com.ruoyi.gk.domain.vo;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.xss.Xss;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsVo {
    /**
     * 用户ID
     */
    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    /**
     * 用户账号
     */
    @Excel(name = "登录名称")
    private String userName;

    /**
     * 用户昵称
     */
    @Excel(name = "用户名称")
    private String nickName;

    /**
     * 用户邮箱
     */
    @Excel(name = "用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码", cellType = Excel.ColumnType.TEXT)
    private String phonenumber;

    /**
     * 用户性别
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;


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
    private Integer ranking;

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
     * 问卷结果id
     */
    @Excel(name = "问卷结果id")
    private Long questionnaireId;

    /**
     * 霍兰德测试结果id
     */
    @Excel(name = "霍兰德测试结果id")
    private String hollandResultId;
    /**
     * 个人收藏集合
     */
    private List<SchoolReduceVo> schoolsCollectList;


    private List<SpecialReduceVo> specialCollectList;


    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    @Excel(name = "最后登录IP", type = Excel.Type.EXPORT)
    private String loginIp;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    private Date loginDate;

    @Excel(name = "备注")
    private String remarks;
    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    public String getNickName() {
        return nickName;
    }

    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    public String getUserName() {
        return userName;
    }

    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    public String getEmail() {
        return email;
    }

    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    public String getPhonenumber() {
        return phonenumber;
    }

}
