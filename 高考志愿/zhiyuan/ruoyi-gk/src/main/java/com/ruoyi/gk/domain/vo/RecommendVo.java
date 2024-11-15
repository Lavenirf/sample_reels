package com.ruoyi.gk.domain.vo;

public class RecommendVo {
    int position;
    String localProvinceName;
    String region;
    String institution_type;
    int userId;
    String institution_level;
    String level2name;

    @Override
    public String toString() {
        return "RecommendVo{" +
                "position=" + position +
                ", localProvinceName='" + localProvinceName + '\'' +
                ", region='" + region + '\'' +
                ", institution_type='" + institution_type + '\'' +
                ", userId=" + userId +
                ", institution_level='" + institution_level + '\'' +
                ", level2name='" + level2name + '\'' +
                '}';
    }

    public String getLevel2name() {
        return level2name;
    }

    public void setLevel2name(String level2name) {
        this.level2name = level2name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLocalProvinceName() {
        return localProvinceName;
    }

    public void setLocalProvinceName(String localProvinceName) {
        this.localProvinceName = localProvinceName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getInstitution_type() {
        return institution_type;
    }

    public void setInstitution_type(String institution_type) {
        this.institution_type = institution_type;
    }

    public String getInstitution_level() {
        return institution_level;
    }

    public void setInstitution_level(String institution_level) {
        this.institution_level = institution_level;
    }
}
