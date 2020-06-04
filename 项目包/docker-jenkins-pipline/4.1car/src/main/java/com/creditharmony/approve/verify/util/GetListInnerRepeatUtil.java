package com.creditharmony.approve.verify.util;

import java.util.ArrayList;
import java.util.List;

import com.creditharmony.approve.localnet.entity.InnerRepeat;


/**
 * 通过传递过来的字符串，解析封装给指定的list
 * @Class Name getListInnerReapet
 * @author 刘燕军
 * @Create In 2015年11月27日
 */
public class GetListInnerRepeatUtil {
	/**
	 * 把页面封装的字符串解析，并封装给对象放到list中去
	 * 2015年11月27日
	 * By 刘燕军  
	 * @param inStr
	 * @return  
	 */
	public static List<InnerRepeat> getList(String inStr){
		List<InnerRepeat> list=new ArrayList<InnerRepeat>();
		//通过“；”把每段信息分割开来
		String [] strings=inStr.split(";");
		for (String string : strings) {
			//通过“，”把id和checked的属性和值 分割开来
			String [] strs=string.split(",");
			InnerRepeat innerRepeat=new InnerRepeat();
			//取出id的值并封装进入对象
			innerRepeat.setId(strs[0].split("=")[1]);
			//判定checked是不是被勾选的，如果勾选，则封装入对象为1，否则为0
			if("true".equals(strs[1].split("=")[1])){
				
				innerRepeat.setExceptionFlag("1");
			}else{
				innerRepeat.setExceptionFlag("0");
			}
			list.add(innerRepeat);
		}
		return list;
	}
	
}
