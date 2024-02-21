package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author derekyi
 * @date 2020/11/22
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	private Map<String, Object> singletonObjects = new HashMap<>();

	/**
	 * 从单例池中获取
	 * @param beanName
	 * @return
	 */
	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}

	/**
	 * 添加到单例池
	 * @param beanName
	 * @param singletonObject
	 */
	protected void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
	}
}
