package com.basilsystems.util;

public class ApplianceModel {

	private String parentDeviceName;
	private String applianceName;
	private Boolean isSlider;
	private int status;
	private Boolean isAuto;
	private String startTime;
	private String endTime;
	private Boolean isScheduledToday;
	private Boolean isScheduledDaily;
	private String weekdays;
	private Boolean isScheduleRepeat;
    
    public String getParentDeviceName() {
		return parentDeviceName;
	}
	public void setParentDeviceName(String parentDeviceName) {
		this.parentDeviceName = parentDeviceName;
	}
	public String getApplianceName() {
		return applianceName;
	}
	public void setApplianceName(String applianceName) {
		this.applianceName = applianceName;
	}
	public Boolean getIsSlider() {
		return isSlider;
	}
	public void setIsSlider(Boolean isSlider) {
		this.isSlider = isSlider;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Boolean getIsAuto() {
		return isAuto;
	}
	public void setIsAuto(Boolean isAuto) {
		this.isAuto = isAuto;
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
	public Boolean getIsScheduledToday() {
		return isScheduledToday;
	}
	public void setIsScheduledToday(Boolean isScheduledToday) {
		this.isScheduledToday = isScheduledToday;
	}
	public Boolean getIsScheduledDaily() {
		return isScheduledDaily;
	}
	public void setIsScheduledDaily(Boolean isScheduledDaily) {
		this.isScheduledDaily = isScheduledDaily;
	}
	public String getWeekdays() {
		return weekdays;
	}
	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
	}
	public Boolean getIsScheduleRepeat() {
		return isScheduleRepeat;
	}
	public void setIsScheduleRepeat(Boolean isScheduleRepeat) {
		this.isScheduleRepeat = isScheduleRepeat;
	}
	
    
}
