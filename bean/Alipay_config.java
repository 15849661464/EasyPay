package com.easypay.bean;

/**
 * 
 * 易支付的配置文件
 * 
 * 1.partner:商户ID
 * 2.key :分配给商户的密钥
 * 
 * @author 左岩
 *
 */
public class Alipay_config {

	//商户ID
	private String partner = "your ID";
	//商户Key
	private String key = "your key"; 
	//签名方式不用更改
	private String sign_type = "MD5";
	//字符编码格式，目前支持GBK或 utf-8
	private String input_charset = "utf-8";
	//访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
	private String transport = "http";
	//支付API地址
	private String apiurl = "http://pay.hackwl.cn/";
	
	
	public String getPartner() {
		return partner;
	}
	public String getKey() {
		return key;
	}
	public String getSign_type() {
		return sign_type;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public String getTransport() {
		return transport;
	}
	public String getApiurl() {
		return apiurl;
	}
	
	
	
}
