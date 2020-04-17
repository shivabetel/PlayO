package com.ss.playo.webapp.run;

import com.ss.playo.webapp.spring.PlayOPersistenceJPAConfig;
import com.ss.playo.webapp.spring.PlayOServiceConfig;
import com.ss.playo.webapp.spring.PlayOWebConfig;
import com.ss.playo.webapp.web.contoller.BookingController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.util.Set;

@SpringBootApplication
public class PlayoserviceApplication {

	private final static Class[] CONFIGS = {
			PlayoserviceApplication.class,
			PlayOWebConfig.class,
			PlayOPersistenceJPAConfig.class,
			PlayOServiceConfig.class
	};

	public static void main(String[] args) {


		new SpringApplicationBuilder(CONFIGS).listeners().run(args);



	//Set<String> set =	AnnotatedElementUtils.getMetaAnnotationTypes(BookingController.class, RequestMapping.class);
		//Set set	 = AnnotatedElementUtils.findAllMergedAnnotations(BookingController.class,RequestMapping.class);

		//new HandlerMethod("BookingController", beanFactory);
	}
//
//	@Override
//	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//		PlayoserviceApplication.beanFactory = beanFactory;
//	}
}
