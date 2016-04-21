package com.gochinatv.accelarator.bmapi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import com.gochinatv.accelarator.bmapi.bean.Business;
import com.gochinatv.accelarator.bmapi.service.BusinessService;
import com.gochinatv.accelarator.bmapi.util.Md5Util;
import com.gochinatv.accelarator.bmapi.util.redis.RedisUtil;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 商家
 * @author limr
 *
 */
@Controller
@RequestMapping("business")
public class BusinessController extends BaseController{
	@Autowired
	private BusinessService businessService;
	
	/**
	 * 登陆
	 * @param userName
	 * @param password
	 * @return
	 */
	@ApiOperation(value = "登陆", httpMethod = "GET", notes = "登陆")
	@RequestMapping("login")
	@ResponseBody
	public Map<String,Object> login(String userName, String password){
		Map<String,Object> result = new HashMap<String,Object>();
		Business business = businessService.getLoginBusiness(userName);
		
		if (business == null) {
			result = this.error("账号不存在");
		}else{
			String truePsw = business.getPassword();
			password = Md5Util.md5(password);
			if(!password.equals(truePsw)){
				result = this.error("用户名/密码错误");
			}else{
				business.setPassword("");
				String token = UUID.randomUUID().toString();
				business.setToken(token);
				RedisUtil.set("token", token, 1*60);
				result = this.success(business);
			}
		}
		
		return result;
	}
	 
}
