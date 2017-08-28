package com.lanwon.service;

import java.util.List;
import com.lanwon.entity.dev.UserDetail;


/**
 * @author dzb
 * @since 2016/7/5 - 22:21
 */
public interface UserDetailService {

    List<UserDetail> selectUserDetail();
    int update(UserDetail userDetail);
    int insert(UserDetail userDetail);
}
