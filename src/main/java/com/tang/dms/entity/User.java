package com.tang.dms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description: 用户实体类
 * @author: Tang
 * @create: 2021-03-04 16:03
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -4606234847008112610L;

    private Integer id;

    private String name;

    private String phone;

    private Short sex;

    private String email;

    private String token;

    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @JSONField(format="yyyy/MM/dd")
    private LocalDateTime creatTime;
}
