package com.ss.playo.webapp.run;

import com.ss.playo.webapp.spring.PlayOJavaSecurityConfig;
import com.ss.playo.webapp.spring.PlayOPersistenceJPAConfig;
import com.ss.playo.webapp.spring.PlayOServiceConfig;
import com.ss.playo.webapp.spring.PlayOWebConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PlayoserviceApplication {


	private final static Class[] CONFIGS = {
			PlayoserviceApplication.class,
			PlayOWebConfig.class,
			PlayOPersistenceJPAConfig.class,
			PlayOServiceConfig.class,
			PlayOJavaSecurityConfig.class
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
