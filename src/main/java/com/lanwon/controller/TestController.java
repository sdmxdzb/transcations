/**
 * 蓝网科技股份有限公司
 */
package com.lanwon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lanwon.entity.dev.UserDetail;
import com.lanwon.entity.prod.UserInfo;
import com.lanwon.service.UserInfoService;
import com.lanwon.service.UserDetailService;


/**
 * @author dzb	
 * @date 2017年8月23日 
 * 
 */
@RestController("")
public class TestController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(value = "/test1")
    public List<UserInfo> selectUser() {
        System.out.println("查询第一个数据源");
        return userInfoService.selectUserInfo();
    }

   @GetMapping(value = "/test2")
    public List<UserDetail> userDetail() {
        System.out.println("查询第二个数据源");
        return userDetailService.selectUserDetail();
    }
	
    
    @PostMapping(value = "/update")
    public String  update() {
        System.out.println("查询第二个数据源");
        return userInfoService.update("张三丰235","张无忌855855588558555555555555555555")+"";
    }
    
    
    @PostMapping(value = "/insertInfo")
    public String  insertInfo() throws Exception {
        System.out.println("查询第二个数据源");
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername("王五");
        userInfo.setJob("打杂");
        int a =userInfoService.insert(userInfo);
        return a+"第一个数据源插入成功";
    }
    
    @PostMapping(value = "/insertDeatil")
    public String  insertDeatil() {
    	UserDetail userDetail =new UserDetail();
    	userDetail.setName("王柳");
    	userDetail.setQq("022522");
        System.out.println("第一个数据源");
        int a = userDetailService.insert(userDetail);
        return a+"第二个数据源插入成功" ;
    }
    
    
    
    
    

}
