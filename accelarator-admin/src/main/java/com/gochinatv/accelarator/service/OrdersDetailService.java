package com.gochinatv.accelarator.service;

import java.util.List;
import com.gochinatv.accelarator.dao.entity.OrdersDetail;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;


/**
 * 
 * @作者 zhuhh
 * @描述     订单详情内容业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface OrdersDetailService  extends BaseService<OrdersDetail>{

	/**
	 * 根据订单id得到订单详情列表
	 * @author limr
	 * @param ordersId
	 * @return
	 */
	List<OrdersDetail> getOrdersDetailList(int ordersId);

	/**
	 * 根据订单id得到清真列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	List<OrdersDetail> getOrdersByPlaceType(int id);
   

}
