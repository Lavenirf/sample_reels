package com.ruoyi.gk.domain;

import java.util.List;

public class specialized {
    List<SchoolMajorScores> schoolMajorScores;
    String name;

    @Override
    public String toString() {
        return "specialized{" +
                "schoolMajorScores=" + schoolMajorScores +
                ", name='" + name + '\'' +
                '}';
    }

    public List<SchoolMajorScores> getSchoolMajorScores() {
        return schoolMajorScores;
    }

    public void setSchoolMajorScores(List<SchoolMajorScores> schoolMajorScores) {
        this.schoolMajorScores = schoolMajorScores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
