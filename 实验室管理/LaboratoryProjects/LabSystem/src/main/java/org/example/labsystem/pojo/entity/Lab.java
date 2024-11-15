package org.example.labsystem.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Lab {
    private Integer id;
    private String title;
    private Integer capacity;
    private Integer labType;
    private Integer collegeType;
    private Integer x;
    private Integer y;
}
