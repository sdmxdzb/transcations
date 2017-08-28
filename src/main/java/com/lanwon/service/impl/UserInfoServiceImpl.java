package com.lanwon.service.impl;


import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lanwon.entity.dev.UserDetail;
import com.lanwon.entity.prod.UserInfo;
import com.lanwon.mapper.prod.UserInfoMapper;
import com.lanwon.service.UserInfoService;




@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;
    
    @Autowired
    private  UserDetailServiceImpl detailServiceImpl ;
    
    public List<UserInfo> selectUserInfo() {
        return userInfoMapper.selectAll();
    }

	//@Transactional(value="prodTransactionManager")
	@Override
	public int update(UserInfo userInfo) {
		int a =userInfoMapper.update(userInfo);
		return a;
	}

    @Transactional(value="prodTransactionManager")
	@Override
	public int update(String name1,String name2) {
    	int c=0;
		UserInfo info = new UserInfo();
		info.setId("1");
		info.setUsername(name1);
		int b=userInfoMapper.update(info);
			if(b>0){
				UserDetail detail = new UserDetail();
				detail.setId(1L);
				detail.setName(name2);
				c=detailServiceImpl.update(detail);
			}
    	
		return c;
	}

	/* (non-Javadoc)
	 * @see com.lanwon.service.UserInfoService#insert(com.lanwon.entity.prod.UserInfo)
	 */
	@Override
	public int insert(UserInfo userInfo) {
		return userInfoMapper.insert(userInfo);
	}
}