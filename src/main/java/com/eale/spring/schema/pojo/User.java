package com.eale.spring.schema.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Author HLD
 * @Date 2021/8/4 0004
 **/
@Data
public class User {

    private String userName;

    private String email;

    private String method;

    private Date date;
}
