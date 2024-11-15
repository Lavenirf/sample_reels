package com.ruoyi.gk.domain;

import java.util.List;
import java.util.Map;

public class SchoolGroup {
    private Long schoolId;
    private long mcid;
    private String schoolName;
    private Map<Long, List<Long>> specialIdGroups; // 特殊ID分组
    private List<SchoolMajorScores> schoolMajorScores;
    List<Map.Entry<Long, List<SpecialDetail>>> specialIdEntries;

    @Override
    public String toString() {
        return "SchoolGroup{" +
                "schoolId=" + schoolId +
                ", mcid=" + mcid +
                ", schoolName='" + schoolName + '\'' +
                ", specialIdGroups=" + specialIdGroups +
                ", schoolMajorScores=" + schoolMajorScores +
                ", specialIdEntries=" + specialIdEntries +
                '}';
    }

    public long getMcid() {
        return mcid;
    }

    public void setMcid(long mcid) {
        this.mcid = mcid;
    }

    public List<Map.Entry<Long, List<SpecialDetail>>> getSpecialIdEntries() {
        return specialIdEntries;
    }

    public void setSpecialIdEntries(List<Map.Entry<Long, List<SpecialDetail>>> specialIdEntries) {
        this.specialIdEntries = specialIdEntries;
    }

    public List<SchoolMajorScores> getSchoolMajorScores() {
        return schoolMajorScores;
    }

    public void setSchoolMajorScores(List<SchoolMajorScores> schoolMajorScores) {
        this.schoolMajorScores = schoolMajorScores;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Map<Long, List<Long>> getSpecialIdGroups() {
        return specialIdGroups;
    }

    public void setSpecialIdGroups(Map<Long, List<Long>> specialIdGroups) {
        this.specialIdGroups = specialIdGroups;
    }
}
