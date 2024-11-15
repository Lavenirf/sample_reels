package org.example.labsystem.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {
    private Integer id;
    private String roleName;
    private Integer roleType;
    private String rights;
}
