/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   01-Nov-2019 
*/
package com.at.projx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
/*import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;*/
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages={"com.at.projx.*"})
@EntityScan(basePackages= "com.at.projx.*")
@EnableJpaRepositories(basePackages="com.at.projx.*")
public class ProjectXApplication  {
	private static final Logger log = LoggerFactory.getLogger(ProjectXApplication.class);

	// =========================================================================

	public static void main(String[] args) {
		SpringApplication.run(ProjectXApplication.class, args);
		log.info("Start of the ProjectXApplication............... ");
	}

	// =========================================================================

	
	/*@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { 
		  return application.sources(ProjectXApplication.class); 
	}*/
	 

	// =========================================================================
}
