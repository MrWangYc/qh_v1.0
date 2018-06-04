package com.qh.model;



import java.io.Serializable;

/**
 * 
 *	实体基类，所有实体类都继承它
 * 
 * @创建人 			王云川
 * @创建日期 		2017-03-22
 * @修改人 			王云川
 * @修改日期 		2017-03-22
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/
@SuppressWarnings("serial")
public class BaseModel implements Serializable,Comparable<Object> {
	

	
	
	public int compareTo(Object o) {
		return 0;
	}
}
