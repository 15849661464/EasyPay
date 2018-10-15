package com.easypay.function;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easypay.bean.Alipay_config;

/**
 *
 * 类名：EpayNotify 功能：彩虹易支付通知处理类 详细：处理易支付接口通知返回
 * 
 * @author 左岩
 *
 */
public class AlipayNotify {

	private Alipay_config alipay_config;
	private String http_verify_url;

	public AlipayNotify(Alipay_config alipay_config) {
		this.alipay_config = alipay_config;
		this.http_verify_url = alipay_config.getApiurl() + "api.php?";
	}

	/**
	 * 针对notify_url验证消息是否是支付宝发出的合法消息
	 * 
	 * @param request
	 * @param response
	 * @return 验证结果
	 * @throws Exception
	 */
	public boolean verifyNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 判断GET来的数组是否为空
		if (request.getParameterMap().isEmpty()) {
			return false;
		} else {
			// 获取Request请求中所带的参数,并将这些个参数封装成一个map
			HashMap<String, String> para_temp = getGetMap(request);
			// 这个获取Map，也就是Request返回的签名参数
			String sign = para_temp.get("sign");
			// 生成签名结果
			boolean isSign = getSignVeryfy(para_temp, sign);
			// 获取支付宝远程服务器ATN结果 （验证是否是支付宝发来的消息）
			String responseTxt = "true";
			// 验证
			// responseTxt的结果不是true，与服务器的设置问题、合作身份者ID、notify_id 一分钟失效有关
			// isSign 的结果不是true，与安全校验码、请求时的参数格式(如:带自定义参数等)、编码格式有关

			// Java中的正则匹配
			String regex = ".*(?i)true$";
			if (Pattern.matches(regex, responseTxt) && isSign) {
				return true;
			} else {
				return false;
			}

		}

	}

	/**
	 * 从Request请求参数中 获取请求参数的 map
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public static HashMap<String, String> getGetMap(HttpServletRequest request) throws Exception {

		Map<String, String[]> requestMap = request.getParameterMap();
		HashMap<String, String> returnMap = new HashMap<String, String>();
	
		for (String key : requestMap.keySet()) {
			returnMap.put(key, new String(request.getParameter(key).getBytes("ISO8859-1"),"UTF-8"));
			//测试获取的字符
		}
		
		return returnMap;

	}

	/**
	 * 功能:获取返回时的签名验证结果
	 * 
	 * @param para_temp
	 *            通知返回来的参数数组
	 * @param sign
	 *            返回的签名结果
	 * @return 签名验证结果
	 * @throws Exception
	 */
	public boolean getSignVeryfy(HashMap<String, String> para_temp, String sign) throws Exception {
		// 出去签名数组中参数数组中的空值和签名参数
		HashMap<String, String> paraFilter = EpayCoreFunction.paraFilter(para_temp);
		// 对待签名参数数组排序
		TreeMap<String, String> para_sort = EpayCoreFunction.argSort(paraFilter);
		// 把数组所有元素，按照 “参数=参数值”的模式用"&"字符拼接成字符串
		String prestr = EpayCoreFunction.createLinkstring(para_sort);
		
		System.out.println("测试创建的字符串为:"+prestr);
		
		boolean isSgin = false;

		isSgin = EpayMD5Function.md5Verify(prestr, sign, this.alipay_config.getKey());

		return isSgin;

	}
	/**
	 * 针对return_url 验证消息是否是支付宝发出的和合法消息
	 * @param request
	 * @param response
	 * @return  验证结果
	 * @throws Exception
	 */
	public boolean verifyReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 判断GET来的数组是否为空
		if (request.getParameterMap().isEmpty()) {
			return false;
		} else {
			// 获取Request请求中所带的参数,并将这些个参数封装成一个map
			HashMap<String, String> para_temp = getGetMap(request);
			// 这个获取Map，也就是Request返回的签名参数
			String sign = para_temp.get("sign");
			// 生成签名结果
			boolean isSign = getSignVeryfy(para_temp, sign);
			// 获取支付宝远程服务器ATN结果 （验证是否是支付宝发来的消息）
			String responseTxt = "true";
			// 验证
			// responseTxt的结果不是true，与服务器的设置问题、合作身份者ID、notify_id 一分钟失效有关
			// isSign 的结果不是true，与安全校验码、请求时的参数格式(如:带自定义参数等)、编码格式有关

			// Java中的正则匹配
			String regex = ".*(?i)true$";
			if (Pattern.matches(regex, responseTxt) && isSign) {
				return true;
			} else {
				return false;
			}

		}

	}

}
