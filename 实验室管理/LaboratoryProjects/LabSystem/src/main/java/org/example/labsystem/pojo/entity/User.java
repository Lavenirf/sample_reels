package org.example.labsystem.pojo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer roleId;
    private Integer isDefault;
    private Role role;
}
