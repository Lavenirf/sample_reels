package com.ruoyi.gk.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推荐专业对象 recommended_majors
 *
 * @author cyz
 * @date 2024-07-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedMajors  implements Comparable<RecommendedMajors>
{
    private Long mcid;
    private String schoolName;
    private Long userId;
    private Long specialId;
    private String specialName;
    private int year;
    private Long schoolId;
    private Long sublevel;
    private double glRatio;
    @Override
    public int compareTo(RecommendedMajors other) {

        return Double.compare(this.glRatio, other.glRatio);
    }
}
