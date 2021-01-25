package com.rong.example.support;


import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.StringUtils;

public class QualifiedBeanNameGenerator extends AnnotationBeanNameGenerator{

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

		//先取自定义名称:Component、Service，Respository，Controller注解的value属性
		if (definition instanceof AnnotatedBeanDefinition) {
			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
			if (StringUtils.hasText(beanName)) {
				// Explicit bean name found.
				return beanName;
			}
		}

		//未自定义的取 全路径名（源代码只取类名，不同路径重名的类，就会引发异常）
		return definition.getBeanClassName();
	}
}
