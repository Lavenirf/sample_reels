package com.ruoyi.gk.domain;

public class SpecialDetail {
    private Long specialId;
    private SchoolMajorScores schoolMajorScores;
    private double probability;

    public SpecialDetail(Long specialId, SchoolMajorScores schoolMajorScores, double probability) {
        this.specialId = specialId;
        this.schoolMajorScores = schoolMajorScores;
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public Long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }

    public SchoolMajorScores getSchoolMajorScores() {
        return schoolMajorScores;
    }

    public void setSchoolMajorScores(SchoolMajorScores schoolMajorScores) {
        this.schoolMajorScores = schoolMajorScores;
    }
}
