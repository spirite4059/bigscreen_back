package com.gochinatv.accelarator.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Role;
import com.gochinatv.accelarator.dao.entity.RoleResource;
import com.gochinatv.accelarator.dao.entity.User;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.RoleService;

/**
 * 
 * @作者 zhuhh
 * @描述   权限控制
 * @创建时间 2016年4月21日 上午11:16:08
 * @修改时间
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
    
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "role/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Role> queryList(int page,int rows,Role role) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Role> list = roleService.getListByEntity(role);
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Role role){
		Map<String,Object> result = this.success(null);
		try{
			roleService.save(role);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Role role){
		Map<String,Object> result = this.success(null);
		try{
			roleService.update(role);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Role role){
		Map<String,Object> result = this.success(null);
		try{
			roleService.deleteByEntity(role);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	/************************************************分配用户、资源************************************************/
	
	/**
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoSelectedUserByRoleId")
	public String gotoSelectedUserByRoleId(Model model,int roleId)throws Exception{
		model.addAttribute("roleId",roleId);
		return "role/selectUser";
	}
	
	/**
	 * 根据角色id查询角色所拥有的用户
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getSelectedUserByRoleId")
	@ResponseBody
	public List<User> getSelectedUserByRoleId(int roleId)throws Exception{
		return roleService.getSelectedUserByRoleId(roleId);
	}
	
	
	/**
	 * 根据角色id查询角色所拥有的用户
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveRoleUser")
	@ResponseBody
	public Map<String,Object> saveRoleUser(String userIds,int roleId)throws Exception{
		Map<String,Object> result = this.success(null);
		try{
			roleService.saveRoleUser(userIds,roleId);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 根据角色id查询角色所拥有的资源
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getSelectedResourceByRoleId")
	@ResponseBody
	public List<RoleResource> getSelectedResourceByRoleId(int roleId)throws Exception{
		return roleService.getSelectedResourceByRoleId(roleId);
	} 
	
	
	/**
	 * 根据角色id查询角色所拥有的用户
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveRoleResource")
	@ResponseBody
	public Map<String,Object> saveRoleResource(String resourceIds,int roleId)throws Exception{
		Map<String,Object> result = this.success(null);
		try{
			roleService.saveRoleResource(resourceIds,roleId);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
}
