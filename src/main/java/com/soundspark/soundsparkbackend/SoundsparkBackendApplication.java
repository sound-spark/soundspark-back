package com.soundspark.soundsparkbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//TODO::ogarnac Data Source error
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SoundsparkBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoundsparkBackendApplication.class, args);
	}

}
