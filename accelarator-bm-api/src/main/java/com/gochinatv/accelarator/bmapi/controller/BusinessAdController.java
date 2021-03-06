package com.gochinatv.accelarator.bmapi.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gochinatv.accelarator.bmapi.bean.BaseVo;
import com.gochinatv.accelarator.bmapi.bean.BusinessAd;
import com.gochinatv.accelarator.bmapi.interceptor.CheckLoginInterceptorAnnotation;
import com.gochinatv.accelarator.bmapi.service.BusinessAdService;
import com.gochinatv.accelarator.bmapi.util.FileChangeLocal;
import com.gochinatv.accelarator.bmapi.util.imageUpload.AmazonS3Tools;
import com.gochinatv.accelarator.bmapi.util.imageUpload.ImageTool;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 商铺广告
 * @author vego
 *
 */
@Controller
@RequestMapping("ba_v1")
public class BusinessAdController  extends BaseController{


	@Autowired
	private BusinessAdService businessAdService;
	
	private static Logger logger = LoggerFactory.getLogger(BusinessAdController.class);
	
	@ApiOperation(value = "上传图片", httpMethod = "GET", notes = "上传图片")
	@RequestMapping(value = "uploadImage", produces = "application/json;charset=utf-8")
	public BaseVo uploadImage(
			@ApiParam(value = "图片文件", required = true) @RequestParam("file") MultipartFile file)
			throws Exception {
		BaseVo baseVo = new BaseVo();

		String imageUrl = "";
		try {
			logger.info("=========file.info:"+file.getBytes()+",name:"+file.getName()+",file"+file.getOriginalFilename());
			imageUrl = upload(file);
			baseVo.setMessage(imageUrl);
		} catch (Exception e) {
			logger.info("===upload.error:"
					+ e.getMessage());
			baseVo.setStatus(1);
			baseVo.setMessage("上传图片失败");
		}
		return baseVo;
	}

	private String upload(MultipartFile file) {
		String result="";
		FileChangeLocal fcl = new FileChangeLocal();
		File localFile = fcl.uploadFileLocal(file, file.getOriginalFilename());
		logger.info("fileName=====11=="+localFile);
		ImageTool it = new ImageTool();
		String fileName = localFile.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
		logger.info("fileName======"+fileName+",suffix==="+suffix);
		File reproduceFile;
		try {
			reproduceFile = it.createThumbnailNew(localFile, suffix, 324, 243);
			result = AmazonS3Tools.uploadFileToAmazon(suffix, reproduceFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@CheckLoginInterceptorAnnotation
	@ApiOperation(value = "查询商家图片列表", httpMethod = "GET", notes = "查询商家图片列表")
	@RequestMapping(value = "/queryNewList", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object>  queryNewList(
			BusinessAd businessAd) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			List<BusinessAd> list = businessAdService.queryList(businessAd);
			result = this.success(list);
		} catch (Exception e) {
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	@CheckLoginInterceptorAnnotation
	@ApiOperation(value = "查询商家图片列表", httpMethod = "GET", notes = "查询商家图片列表")
	@RequestMapping(value = "/queryList", produces = "application/json;charset=utf-8")
	@ResponseBody
	public List<BusinessAd> queryList(
			BusinessAd businessAd) throws Exception{
		List<BusinessAd> list = businessAdService.queryList(businessAd);
		return list;
	}
	@CheckLoginInterceptorAnnotation
	@ApiOperation(value = "保存商家图片", httpMethod = "GET", notes = "保存商家图片")
	@RequestMapping(value = "/save", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> save(BusinessAd businessAd){
		Map<String,Object> result = this.success(null);
		try{
			businessAdService.save(businessAd);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	@CheckLoginInterceptorAnnotation
	@ApiOperation(value = "更新商家图片", httpMethod = "GET", notes = "更新商家图片")
	@RequestMapping(value = "/update", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> update(BusinessAd businessAd){
		Map<String,Object> result = this.success(null);
		try{
			businessAdService.update(businessAd);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	@CheckLoginInterceptorAnnotation
	@ApiOperation(value = "删除商家图片信息", httpMethod = "GET", notes = "删除商家图片信息")
	@RequestMapping(value = "/delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> delete( @ApiParam(value = "商家图片ID", required = true)int businessAdId){
		Map<String,Object> result = this.success(null);
		try{
			businessAdService.delete(businessAdId);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
}
