package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleEnum;
import com.example.demo.repository.RoleRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoApplication {
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
    return () -> {
			Role checkAdmin = roleRepository.findByName(RoleEnum.ROLE_ADMIN).orElse(null);
				if(checkAdmin == null) {
					log.info("Set role admin");
					Role roleAdmin = new Role();
					roleAdmin.setName(RoleEnum.ROLE_ADMIN);
					roleRepository.save(roleAdmin);
				}
			Role checkUser = roleRepository.findByName(RoleEnum.ROLE_USER).orElse(null);
				if(checkUser == null) {
					log.info("Set role user");
					Role roleUser = new Role();
					roleUser.setName(RoleEnum.ROLE_USER);
					roleRepository.save(roleUser);
				}
			};
		}
		
	@Bean
	public WebMvcConfigurer getCorsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedOrigins("http://localhost:3000", "https://booking-vaksin-alta.herokuapp.com/", "https://test-vaksinqu.vercel.app/" ).allowCredentials(true).allowedMethods("GET", "PUT", "POST", "DELETE");
			}
		};
	}

}
