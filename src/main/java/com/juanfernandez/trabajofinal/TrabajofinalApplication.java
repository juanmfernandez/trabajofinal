package com.juanfernandez.trabajofinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.juanfernandez"})
public class TrabajofinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabajofinalApplication.class, args);
	}

}
