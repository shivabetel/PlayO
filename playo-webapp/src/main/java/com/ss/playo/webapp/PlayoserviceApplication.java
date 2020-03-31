package com.ss.playo.webapp;

import com.ss.playo.common.web.RestResponseEntityExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:mysql-properties.properties")
@Import(RestResponseEntityExceptionHandler.class)
public class PlayoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayoserviceApplication.class, args);
	}

}
