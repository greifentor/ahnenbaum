package de.ollie.ahnenbaum;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.ahnenbaum")
@EnableJpaRepositories(basePackages = { "de.ollie.ahnenbaum.persistence.repository" })
public class AhnenbaumServiceStarter {

	public static void main(String[] args) {
		SpringApplication.run(AhnenbaumServiceStarter.class, args);
	}
}
