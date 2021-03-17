package com.tang.dms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.dms.entity.Notice;
import com.tang.dms.entity.bo.NoticeBO;
import com.tang.dms.entity.bo.NoticeDetailBO;

import java.util.List;

public interface NoticeService extends IService<Notice> {
    List<NoticeBO> getNoticeList();

    NoticeDetailBO getNoticeDetail(Integer id);

}
