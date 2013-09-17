package com.hosmerlake.rss.common.beans;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

public class CopyUtil {
	
	/**
	 * 
	 * @param dest
	 * @param original
	 * @param propNames
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
    public static void beanCopy(Object dest, Object original, String[] propNames) throws InvocationTargetException, IllegalAccessException {  
    	//Copy all properties from original to destination object and ignore all properties on DefaultParameters
    	BeanUtils.copyProperties(original, dest, propNames);
    }
}

