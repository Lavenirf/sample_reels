package org.example.labsystem.pojo.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Book {
    private Integer id;
    private Date bookTime;
    private Integer labId;
    private Integer bookClass;
    private String bookReason;
    private Integer bookUser;
    private Integer bookState;
    private Lab lab;
    private String useName;
}
