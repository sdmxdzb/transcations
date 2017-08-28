package com.lanwon.service.impl;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.client.RestTemplate;

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

	@Autowired
	private RestTemplate restTemplate;
    
    public List<UserDetail> selectUserDetail() {
        return userDetailMapper.selectAll();
    }


	@Transactional(value="devTransactionManager")
	@Override
	public int update(UserDetail userDetail){
		int b=userDetailMapper.update(userDetail);
		return b;
	}

	@Transactional(value="devTransactionManager")
	@Override
	public int insert(UserDetail userDetail) {
		int a= userDetailMapper.insert(userDetail);
		 if(a>0){
		  Map<String, Object> uriVariables = new HashMap<String, Object>();
          uriVariables.put("phone", "151xxxxxxxx");
          uriVariables.put("msg", "测试短信内容");
          ResponseEntity<String> str=	restTemplate.postForEntity("http://192.168.121.215:8080/insertInfo",uriVariables, String.class );
      	  System.out.println(str);
		 }
		return a;
	}
}
