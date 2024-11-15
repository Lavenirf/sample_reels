package com.core.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Token {

    private String token;

    private String type;

    private Long studentId;

    private Long teacherId;

    private Long userId;
}
