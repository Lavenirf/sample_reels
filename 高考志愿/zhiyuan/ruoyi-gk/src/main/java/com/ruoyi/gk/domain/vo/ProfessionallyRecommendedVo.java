package com.ruoyi.gk.domain.vo;

import com.ruoyi.gk.domain.RecommendedMajors;

import java.util.ArrayList;
import java.util.List;

public class ProfessionallyRecommendedVo {
    private List<RecommendedMajors> chong;
    private List<RecommendedMajors> wen;
    private List<RecommendedMajors> bao;
    List<RecommendedMajors> recommendedMajors;
    public ProfessionallyRecommendedVo() {
        // 初始化 List 对象
        this.chong = new ArrayList<>();
        this.wen = new ArrayList<>();
        this.bao = new ArrayList<>();
        this.recommendedMajors = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "ProfessionallyRecommendedVo{" +
                "chong=" + chong +
                ", wen=" + wen +
                ", bao=" + bao +
                ", recommendedMajors=" + recommendedMajors +
                '}';
    }

    public List<RecommendedMajors> getChong() {
        return chong;
    }

    public void setChong(List<RecommendedMajors> chong) {
        this.chong = chong;
    }

    public List<RecommendedMajors> getWen() {
        return wen;
    }

    public void setWen(List<RecommendedMajors> wen) {
        this.wen = wen;
    }

    public List<RecommendedMajors> getBao() {
        return bao;
    }

    public void setBao(List<RecommendedMajors> bao) {
        this.bao = bao;
    }

    public List<RecommendedMajors> getRecommendedMajors() {
        return recommendedMajors;
    }

    public void setRecommendedMajors(List<RecommendedMajors> recommendedMajors) {
        this.recommendedMajors = recommendedMajors;
    }
}
