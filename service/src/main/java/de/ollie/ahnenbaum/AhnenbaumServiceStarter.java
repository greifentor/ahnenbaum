package de.ollie.ahnenbaum;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.ahnenbaum")
public class AhnenbaumServiceStarter {

	public static void main(String[] args) {
		SpringApplication.run(AhnenbaumServiceStarter.class, args);
	}
}
