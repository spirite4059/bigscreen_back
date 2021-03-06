package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.SystemAdvertisementDao;
import com.gochinatv.accelarator.dao.entity.SystemAdvertisement;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.SystemAdvertisementService;

/**
 * 
 * @作者 zhuhh
 * @描述   系统广告业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class SystemAdvertisementServiceImpl extends BaseServiceImpl<SystemAdvertisement> implements SystemAdvertisementService{

	@Autowired
	private SystemAdvertisementDao systemAdvertisementDao;
	
	@Override
	protected BaseDao<SystemAdvertisement> getDao() {
		return systemAdvertisementDao;
	}
	
	
}
