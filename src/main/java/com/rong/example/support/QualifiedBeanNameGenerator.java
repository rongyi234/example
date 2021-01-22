package com.rong.example.support;


import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.StringUtils;

public class QualifiedBeanNameGenerator extends AnnotationBeanNameGenerator{

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		//先取定义名称
		if (definition instanceof AnnotatedBeanDefinition) {
			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
			if (StringUtils.hasText(beanName)) {
				// Explicit bean name found.
				return beanName;
			}
		}
		return definition.getBeanClassName();
	}
}
