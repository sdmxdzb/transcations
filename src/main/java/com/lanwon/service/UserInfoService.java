package com.lanwon.service;


import java.util.List;

import com.lanwon.entity.prod.UserInfo;

/**
 * @author dzb
 * @since 2016/7/5 - 22:18
 */
public interface UserInfoService {

    List<UserInfo> selectUserInfo();
    
    int update(UserInfo userInfo);
    
    int update(String name1,String name2);
    
    int insert(UserInfo userInfo);
    
}
