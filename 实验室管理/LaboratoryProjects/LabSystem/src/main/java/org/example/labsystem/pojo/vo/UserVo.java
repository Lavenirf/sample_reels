package org.example.labsystem.pojo.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.labsystem.pojo.entity.Role;
import org.example.labsystem.pojo.entity.User;

@Data
@NoArgsConstructor
public class UserVo {
    private User user;
    private String token;
    public UserVo(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
