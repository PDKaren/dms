package com.tang.dms.entity.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 公告详情
 * @author: Tang
 * @create: 2021-03-16 16:22
 */
@Data
public class NoticeDetailBO implements Serializable {
    private static final long serialVersionUID = 7872405763660008378L;
    private Integer id;

    private String title;

    private String content;

    private String adminName;

    private String creatDate;
}
