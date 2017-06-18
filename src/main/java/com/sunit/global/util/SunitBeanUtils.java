package com.sunit.global.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

public class SunitBeanUtils extends BeanUtils {

	public static void copyProperties(Object source, Object target) throws BeansException {
		copyProperties(source, target, null, null);
	} 
	
	public static void copyProperties(Object source, Object target,String[] ignoreProperties) throws BeansException {
		copyProperties(source, target, null, ignoreProperties);
	} 
	
	private static void copyProperties(Object source, Object target,
			Class<?> editable, String[] ignoreProperties) throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null"); 
		
		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException("Target class ["
						+ target.getClass().getName()
						+ "] not assignable to Editable class ["
						+ editable.getName() + "]");
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null) ? Arrays
				.asList(ignoreProperties) : null;

		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null
					&& (ignoreProperties == null || (!ignoreList
							.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source
						.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass()
								.getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						
						if(value==null) continue;      
						
						/**
						 * 	if(AbstractID.class.isAssignableFrom(value.getClass())){
							Object o = readMethod.invoke(target); 
							if(o==null){ 
								Method writeMethod = targetPd.getWriteMethod();
								if (!Modifier.isPublic(writeMethod.getDeclaringClass()
										.getModifiers())) {
									writeMethod.setAccessible(true); 
								}
								writeMethod.invoke(target, value);
								continue;
							}
							copyProperties(value,o);  
							continue;
						}
						
						 */
					
						Method writeMethod = targetPd.getWriteMethod();
						if (!Modifier.isPublic(writeMethod.getDeclaringClass()
								.getModifiers())) {
							writeMethod.setAccessible(true);
						}
						writeMethod.invoke(target, value);
					} catch (Throwable ex) {
						throw new FatalBeanException(
								"Could not copy properties from source to target",
								ex);
					}
				}
			}
		}
	}
	
	
	/**
	 * 同步两个对象相的字段
	* @Title: syncProperties 
	* @Description: 
	* @param @param source
	* @param @param target
	* @param @param editable
	* @param @param ignoreProperties
	* @param @throws BeansException     
	* @return void  
	* @throws 
	* @author joye 
	* Jul 8, 2016 5:52:00 PM
	 */
	public static void syncProperties(Object source, Object target,
			Class<?> editable, String[] ignoreProperties) throws BeansException {

		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");  
		
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<String> ignoreList = (ignoreProperties != null) ? Arrays
				.asList(ignoreProperties) : null;
		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null
					&& (ignoreProperties == null || (!ignoreList
							.contains(targetPd.getName())))) {
				PropertyDescriptor sourcePd = getPropertyDescriptor(source
						.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass()
								.getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						if(value==null) continue;      
						Method writeMethod = targetPd.getWriteMethod();
						if (!Modifier.isPublic(writeMethod.getDeclaringClass()
								.getModifiers())) {
							writeMethod.setAccessible(true);
						} 
						writeMethod.invoke(target, value); 
					} catch (Throwable ex) {
						throw new FatalBeanException(
								"Could not copy properties from source to target",
								ex);
					}
				}
			}
		}
	}
	
	
	
	
}
