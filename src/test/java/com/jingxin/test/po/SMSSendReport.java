package com.jingxin.test.po;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 短信发送报告记录表
 * @author cyh
 *
 */
@Entity
@Table(name="user_sms_report")
public class SMSSendReport implements Serializable{

	private static final long serialVersionUID = 8324665948711083811L;
	
	private String requestId;//请求Id
	private Timestamp sendTime;//发送时间
	private Timestamp receiveTime;//接收时间
	private String sendPhone;//发送号码
	private String receivePhone;//接收号码
	private Integer status;//状态
	private Timestamp reportTime = new Timestamp(System.currentTimeMillis());//获取报告时间
	
	@Id
	@Column(length=64)
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	@Column
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	@Column
	public Timestamp getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}
	@Column(length=64)
	public String getSendPhone() {
		return sendPhone;
	}
	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}
	@Column(length=64)
	public String getReceivePhone() {
		return receivePhone;
	}
	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}
	@Column(nullable=false)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(nullable=false)
	public Timestamp getReportTime() {
		return reportTime;
	}
	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}
	
	//使用链式操作，方便设置Bean值进行保存
	public SMSSendReport setReId(String requestId) {
		this.requestId = requestId;
		return this;
	}
	public SMSSendReport setSendT(Timestamp sendTime) {
		this.sendTime = sendTime;
		return this;
	}
	public SMSSendReport setReceiveT(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
		return this;
	}
	public SMSSendReport setSend(String sendPhone) {
		this.sendPhone = sendPhone;
		return this;
	}
	public SMSSendReport setReceive(String receivePhone) {
		this.receivePhone = receivePhone;
		return this;
	}
	public SMSSendReport setState(Integer status) {
		this.status = status;
		return this;
	}
	public SMSSendReport setReportT(Timestamp reportTime) {
		this.reportTime = reportTime;
		return this;
	}
}
