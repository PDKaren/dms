package com.tang.dms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @description: 学生实体类
 * @author: Tang
 * @create: 2021-03-04 22:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends User {
    private static final long serialVersionUID = -1797585597429565029L;
    private Integer sid;

    private String sno;

    private Integer uid;

    private String password;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @JSONField(format="yyyy/MM/dd")
    private LocalDateTime creatTime;
}
