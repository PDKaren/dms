package com.tang.dms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.dms.entity.Notice;
import com.tang.dms.entity.bo.NoticeBO;
import com.tang.dms.entity.bo.NoticeDetailBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice> {

    List<NoticeBO> selectNoticeTitle();

    NoticeDetailBO selectNoticeById(@Param("id") Integer id);
}
