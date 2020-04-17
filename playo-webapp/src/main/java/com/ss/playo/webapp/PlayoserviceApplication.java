package com.ss.playo.webapp;

import com.ss.playo.common.web.RestResponseEntityExceptionHandler;
import com.ss.playo.webapp.service.IUserService;
import com.ss.playo.webapp.service.impl.UserServiceImpl;
import com.ss.playo.webapp.web.contoller.BookingController;
import com.ss.playo.webapp.web.contoller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.lang.reflect.Method;
import java.util.Properties;

@SpringBootApplication
@PropertySource("classpath:mysql-properties.properties")
@Import({RestResponseEntityExceptionHandler.class})

public class PlayoserviceApplication {

	private  static IUserService userService = new UserServiceImpl();

	public static void main(String[] args) {

		SpringApplication.run(PlayoserviceApplication.class, args);

//	   Method method =  RefelectionUtils.findMethod(UserServiceImpl.class, "findByEmailId", String.class);
//	   try {
//		   method.invoke(userService, "shiva.betel@gmail.com");
//	   }catch (Exception e){
//
//	   }
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("ssorghelpdesk@gmail.com");
		mailSender.setPassword("Betelecom06*");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

}
