package com.gochinatv.accelarator.api.service;

import java.util.List;

import com.gochinatv.accelarator.api.bean.AdInfo;
import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.ImageAdInfo;
import com.gochinatv.accelarator.api.vo.ResponseDeviceInfo;

public interface DeviceService {

	public void  saveDeviceImage(Device device);
	
	public List<ImageAdInfo> queryImageAdInfoList();
	
	public ResponseDeviceInfo getDeviceInfo(String mac)  throws Exception ;
	
	/**
	 * 返回time时间对于mac的 视频广告列表信息
	 * @param mac
	 * @param time
	 * @return
	 */
	public List<AdInfo> getAdInfo(String mac,String time);

}