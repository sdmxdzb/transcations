package com.lanwon.service.impl;


import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.lanwon.entity.dev.UserDetail;
import com.lanwon.mapper.dev.UserDetailMapper;
import com.lanwon.service.UserDetailService;

/**
 * @author dzb
 * @since 2016/7/5 - 22:21
 */
@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private UserDetailMapper userDetailMapper;

    public List<UserDetail> selectUserDetail() {
        return userDetailMapper.selectAll();
    }


	@Transactional(value="devTransactionManager")
	@Override
	public int update(UserDetail userDetail){
		int b=userDetailMapper.update(userDetail);
		return b;
	}


	/* (non-Javadoc)
	 * @see com.lanwon.service.UserDetailService#insert(com.lanwon.entity.dev.UserDetail)
	 */
	@Override
	public int insert(UserDetail userDetail) {
		return userDetailMapper.insert(userDetail);
	}
}
