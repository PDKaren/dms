package com.tang.dms.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description: 公告
 * @author: Tang
 * @create: 2021-03-13 12:25
 */
@Data
public class Notice implements Serializable {
    private static final long serialVersionUID = 2069924208792648477L;

    private Integer id;

    private String title;

    private String content;

    private Integer adminId;

    private Short status;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime creatTime;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
