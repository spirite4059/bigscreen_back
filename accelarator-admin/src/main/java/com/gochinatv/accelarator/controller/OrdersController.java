package com.gochinatv.accelarator.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Orders;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.framework.web.base.utils.DateUtils;
import com.gochinatv.accelarator.service.OrdersService;


/**
 * 
 * @作者 zhuhh
 * @描述 订单控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController{
    
	@Autowired
	private OrdersService ordersService;

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "orders/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Orders> queryList(int page,int rows,Orders orders) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Orders> list = ordersService.getOrdersList(orders);
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
	/******************************************************************************************/
	/**
	 * 到在播广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoPlayList")
	public String gotoPlayList(Model model) throws Exception{
		return "orders/play_list";
	}
	
	/**
	 * 查询在播广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPlayList")
	@ResponseBody
	public PageInfo<Orders> queryPlayList(int page,int rows,Orders orders) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Orders> list = ordersService.queryPlayList(orders);
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(list);
		return pageInfo;
	}
	
	
	/*****************************************************************************************************************/
	/**
	 * 到可用广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoAvailableList")
	public String gotoAvailableList() throws Exception{
		return "orders/available_list";
	}
	
	/**
	 * 查询可用广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAvailableList")
	@ResponseBody
	public List<Orders> queryAvailableList(Orders orders) throws Exception{
		List<Orders> list = ordersService.getAvailableList(orders);
		return list;
	}
	
	
	/**
	 * 生成订单
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/createOrder")
	public String createOrder(Orders orders,Model model) throws Exception{
		orders.setOrderNo(DateFormatUtils.format(new Date(),DateUtils.YYYY_MM_DD_HH_MM_SS_SSS));
		model.addAttribute("orders",orders);
		return "orders/order_preview";
	}
	
	
	/**
	 * 订单详情
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/orderDetail")
	@ResponseBody
	public List<Orders> orderDetail(Orders orders) throws Exception{
		List<Orders> list = ordersService.getRetryOrdersList(orders);
		return list;
	}
	
	/**
	 * 保存订单
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveOrder")
	@ResponseBody
	public Map<String,Object> saveOrder(Orders orders){
		Map<String,Object> result = this.success(null);
		try{
			ordersService.save(orders);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	/*********************************************************************************************/
}
