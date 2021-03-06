package com.gochinatv.accelarator.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.dao.entity.TwoFourBm;
import com.gochinatv.accelarator.dao.entity.TwoFourBmArea;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.TwoFourBmService;
import com.gochinatv.accelarator.service.TwoFourContentService;

/**
 * 
 * @作者 administrator
 * @描述   2-4号位管理控制层
 * @创建时间  2016-5-9
 * @修改时间
 */
@Controller
@RequestMapping("/two_four_bm")
public class TwoFourBmController extends BaseController{
    
	@Autowired
	private TwoFourBmService twoFourBmService;
	
	@Autowired
	private TwoFourContentService twoFourContentService;
	
	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "two_four_bm/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<TwoFourBm> queryList(int page,int rows,TwoFourBm twoFourBm) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<TwoFourBm> list = twoFourBmService.getListByEntity(twoFourBm);
		PageInfo<TwoFourBm> pageInfo = new PageInfo<TwoFourBm>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(TwoFourBm twoFourBm){
		Map<String,Object> result = this.success(null);
		try{
			twoFourBm.setCreateTime(new Date());
			twoFourBmService.save(twoFourBm);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(TwoFourBm twoFourBm){
		Map<String,Object> result = this.success(null);
		try{
			twoFourBmService.update(twoFourBm);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(TwoFourBm twoFourBm){
		Map<String,Object> result = this.success(null);
		try{
			twoFourBmService.deleteByEntity(twoFourBm);
			twoFourContentService.deleteByBmId(twoFourBm.getId());
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	/**
	 * 发放
	 * @param twoFourBmArea
	 * @return
	 */
	@RequestMapping("/delivery")
	@ResponseBody
	public Map<String,Object> delivery(TwoFourBmArea twoFourBmArea){
		Map<String, Object> result = this.success(null);
		try{
			twoFourBmService.delivery(twoFourBmArea);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	/**
	 * 校验选中地区是否已有发布且时间冲突
	 * @param twoFourBmArea
	 * @return
	 */
	@RequestMapping("/checkCode")
	@ResponseBody
	public Map<String,Object> checkCode(TwoFourBmArea twoFourBmArea){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			List<TwoFourBmArea> tList = twoFourBmService.checkCode(twoFourBmArea);
			result = this.success(tList);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	/**
	 * 得到选中的地区
	 * @param twoFourBmId
	 * @return
	 */
	@RequestMapping("/getCheckNodes")
	@ResponseBody
	public Map<String,Object> getCheckNodes(int twoFourBmId){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			String checkNodes = twoFourBmService.getCheckNodes(twoFourBmId);
			result = this.success(checkNodes);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @param parentMethod
	 * @return
	 */
	@RequestMapping(value = "/gotoTwoFourBmLookUp")
    public String gotoTwoFourBmLookUp(Model model,@RequestParam(value = "parentMethod") String parentMethod){
		model.addAttribute("parentMethod", parentMethod);
		return "two_four_bm/lookUpForContent";
    }
	
}
