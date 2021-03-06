package com.gochinatv.accelarator.service.impl;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gochinatv.accelarator.dao.entity.Area;
import com.gochinatv.accelarator.service.AreaService;
import com.gochinatv.accelarator.util.GlobalUtils;


/**
 * 
 * @作者 zhuhh
 * @描述    系统初始化加载json文件配置
 * @创建时间 2016年4月13日 上午10:17:45
 * @修改时间
 */
@Component
public class Initialization implements InitializingBean{
    
	private static final Logger logger = LoggerFactory.getLogger(Initialization.class);
	
	@Autowired
	private AreaService areaService;
	
	@Value(value="${JSON_PATH}")
	private String JSON_PATH;

	
	/**
	 * 系统
	 */
	public void afterPropertiesSet() throws Exception {
		String[] filePaths = JSON_PATH.split(",");
		for (String filePath : filePaths) {
			File file = new File(filePath);
			if(file.exists()){
				logger.info("***加载JSON文件中，输出文件路径为：{}",JSON_PATH);
				areaService.createAreaJson(null,filePath);
				logger.info("***加载JSON文件完成***");
				
				logger.info("***初始化AREA数据至静态Map中");
				GlobalUtils.AREA_CODE_NAME.clear();
				List<Area> list = areaService.getList();
				for (Area area : list) {
					GlobalUtils.AREA_CODE_NAME.put(area.getAreaCode(),area.getName());
				}
				logger.info("***初始化完成静态Map**，共{}条",list.size());
				break;
			}
		}
	}
	
	
	   
}
