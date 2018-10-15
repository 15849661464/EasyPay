package com.easypay.function;

import java.util.HashMap;
import java.util.TreeMap;

public class EpayCoreFunction {

	/**
	 * 功能  去除数组中的空值和签名参数
	 * @param parameter  签名数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 * 
	 */
	public static HashMap<String, String> paraFilter(HashMap<String,String> parameter)
	{
		HashMap filterPara = new HashMap<String,String>();
		for(String key : parameter.keySet())
		{
			//此处有个主意事项！判断两个字符串是否相等不用 == 要用 equals()  不然程序过不去
			if(key.equals("sign")||key.equals("sign_type")||parameter.get(key).isEmpty())
			{
				continue;
			}
			else
			{
				filterPara.put(key, parameter.get(key));
			}
		}
		return filterPara;
	}
	
	/**
	 * 功能:对数组进行排序
	 * @param parameter
	 * @return
	 */
	
	public static TreeMap<String, String> argSort(HashMap<String,String> parameter)
	{
		TreeMap<String,String> treeMap = new TreeMap<String,String>(parameter);
		return  treeMap;
	}
	
	
	/**
	 * 
	 * 功能：把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * @param para_sort 需要拼接的数组
	 * return 拼接完成以后的字符串
	 */
	public static String createLinkstring(TreeMap<String,String> para_sort)
	{
		String result="";
		for(String key:para_sort.keySet())
		{
			result+=key+"="+para_sort.get(key)+"&";
		}
		result=result.substring(0, result.length()-1);
		
		return result;
		
	}
	
	
	
}
