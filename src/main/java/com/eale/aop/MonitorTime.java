package com.eale.aop;

import lombok.Data;

import java.util.Date;

/**
 * @Author HLD
 * @Date 2021/7/6 0006
 **/
@Data
public class MonitorTime {

    private String className;

    private String methodName;

    private Date logTime;

    private Long consumeTime;


}
