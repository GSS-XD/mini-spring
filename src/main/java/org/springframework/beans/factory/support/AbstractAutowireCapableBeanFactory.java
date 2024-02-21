package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author derekyi
 * @date 2020/11/22
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

	private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

	/**
	 * 创建bean
	 * @param beanName
	 * @param beanDefinition
	 * @return
	 * @throws BeansException
	 */
	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
		return doCreateBean(beanName, beanDefinition);
	}

	/**
	 * 创建bean
	 * @param beanName
	 * @param beanDefinition
	 * @return
	 */
	protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
		Object bean = null;
		try {
			bean = createBeanInstance(beanDefinition);
		} catch (Exception e) {
			throw new BeansException("Instantiation of bean failed", e);
		}

		//将实例缓存到单例池
		addSingleton(beanName, bean);
		return bean;
	}

	/**
	 * 创建Bean实例
	 * @param beanDefinition
	 * @return
	 */
	protected Object createBeanInstance(BeanDefinition beanDefinition) {
		//获取实例化策略
		InstantiationStrategy instantiationStrategy = getInstantiationStrategy();
		//使用简单实例化策略的实例化方法
		Object instantiate = instantiationStrategy.instantiate(beanDefinition);
		return instantiate;
	}

	/**
	 * 获取实例化策略
	 * @return
	 */
	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}
}
