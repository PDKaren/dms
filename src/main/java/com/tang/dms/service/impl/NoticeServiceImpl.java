package com.tang.dms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.dms.entity.Notice;
import com.tang.dms.entity.bo.NoticeBO;
import com.tang.dms.entity.bo.NoticeDetailBO;
import com.tang.dms.mapper.NoticeMapper;
import com.tang.dms.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 公告
 * @author: Tang
 * @create: 2021-03-13 12:33
 */
@Service
@Transactional
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper,Notice> implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public List<NoticeBO> getNoticeList() {
        return noticeMapper.selectNoticeTitle();
    }

    @Override
    public NoticeDetailBO getNoticeDetail(Integer id) {
        return noticeMapper.selectNoticeById(id);
    }
}
