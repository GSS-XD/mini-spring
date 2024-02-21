package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author derekyi
 * @date 2020/11/22
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

	/**
	 * 获取Bean
	 * @param name
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object getBean(String name) throws BeansException {
		//丛单例池中获取
		Object bean = getSingleton(name);
		//如果单例池中能获取到就直接返回
		if (bean != null) {
			return bean;
		}
		//单例池中不存在就创建
		BeanDefinition beanDefinition = getBeanDefinition(name);
		//返回创建的Bean
		return createBean(name, beanDefinition);
	}

	protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

	protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
