package org.example.labsystem.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.List;
@Data
@NoArgsConstructor
public class Right {
    private Integer id;
    private String title;
    private String icon;
    private String path;
    private List<Right> children;
}
