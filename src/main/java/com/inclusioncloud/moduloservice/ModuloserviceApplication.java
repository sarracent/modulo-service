package com.inclusioncloud.moduloservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.inclusioncloud.moduloservice")
public class ModuloserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuloserviceApplication.class, args);
	}

}
