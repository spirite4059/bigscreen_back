package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;

/**
 * 
 * @作者 zhuhh
 * @描述 排播列表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class PlayList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;//主键id，因为保存排播组合的特殊性，所有这里特殊处理
	private int deviceId;//设备id
	private int placeId;//店铺id
	private String cityCode;//地市code
	private int type;//店铺类型  （1：餐厅  2：大使馆   3：商场   4：美甲区    5：其它）
	private String startTime;// 开始时间
	private String endTime;// 结束时间

	
	private String title;//临时 广告标题

	private String yesterdayTime;// 临时时间
	
	
	
	public String getYesterdayTime() {
		return yesterdayTime;
	}

	public void setYesterdayTime(String yesterdayTime) {
		this.yesterdayTime = yesterdayTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
