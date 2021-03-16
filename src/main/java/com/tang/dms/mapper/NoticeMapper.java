package com.tang.dms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.dms.entity.Notice;
import com.tang.dms.entity.bo.NoticeBO;

import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice> {

    List<NoticeBO> selectNoticeTitle();
}
