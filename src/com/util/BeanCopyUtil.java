package com.util;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;

/**
 * 
 * bean 拷贝工具类 忽略null
 * 
 * @创建人 			王云川
 * @创建日期 		2017-03-22
 * @修改人 			王云川
 * @修改日期 		2017-03-22
 * @版本号 			1.0.0
 * @版权所有 		XX科技
*/
public class BeanCopyUtil extends BeanUtilsBean {
	
	@Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value == null) return;
        if(value instanceof Integer && Integer.parseInt(value.toString()) == 0) return;//排除
        super.copyProperty(dest, name, value);
    }
	
	/**
	 * 拷贝
	 * @param dest		目标对象
	 * @param source	原来的对象
	 */
	public static void copy(Object dest, Object source) {
		try {
			BeanUtilsBean bean = new BeanCopyUtil();
			bean.copyProperties(dest, source);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
