package com.lanwon.mapper.dev;

import java.util.List;

import com.lanwon.entity.dev.UserDetail;

public interface UserDetailMapper {
	
	int update(UserDetail userDetail);
	
	/**
	 * @return
	 */
	List<UserDetail> selectAll();
    int insert(UserDetail userDetail);
}