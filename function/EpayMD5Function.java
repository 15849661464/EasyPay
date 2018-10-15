package com.easypay.function;



import com.easypay.utils.myMD5;


public class EpayMD5Function {


	/**
	 * 功能:签名字符串
	 * @param prestr 需要签名的字符串
	 * @param key 私钥
	 * @return  签名结果
	 * @throws Exception
	 */
	public static String md5Sign(String prestr, String key) throws Exception {
		prestr += key;
		prestr=myMD5.Md5Encode(prestr);
		return prestr;
	}
	
	
	/**
	 * 功能:验证签名
	 * @param prestr 需要签名的字符串
	 * @param sign	签名结果
	 * @param key	私钥
	 * @return   签名结果（boolean）
	 * @throws Exception
	 */
	
	public static boolean md5Verify(String prestr,String sign,String key) throws Exception
	{
		prestr+=key;
		String mysgin = myMD5.Md5Encode(prestr);
		if(mysgin.equals(sign))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	

}
