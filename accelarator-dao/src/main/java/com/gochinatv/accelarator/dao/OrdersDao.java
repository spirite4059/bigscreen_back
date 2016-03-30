package com.gochinatv.accelarator.dao;

import java.util.List;

import com.gochinatv.accelarator.dao.entity.Orders;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * @作者 zhuhh
 * @描述     订单数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface OrdersDao  extends BaseDao<Orders>{
   
    
	/**
	 * 在播广告列表
	 * @param orders
	 * @return
	 */
	public List<Orders> queryPlayList(Orders orders);
}