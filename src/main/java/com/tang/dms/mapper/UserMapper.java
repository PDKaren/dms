package com.tang.dms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.dms.entity.Student;
import com.tang.dms.entity.User;

/**
 * @description: 用户mapper
 * @author: Tang
 * @create: 2021-03-04 16:17
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取学生用户信息
     * @param sno 学号
     * @return 学生信息
     */
    Student getStudentUser(String sno);
}
