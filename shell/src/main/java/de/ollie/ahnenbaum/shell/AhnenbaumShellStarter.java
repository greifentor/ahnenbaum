package de.ollie.ahnenbaum.shell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Starter class for the Ahnenbaum shell.
 * 
 * @author ollie (08.11.2024)
 */
@SpringBootApplication
@ComponentScan("de.ollie")
public class AhnenbaumShellStarter {

	public static void main(String[] args) {
		SpringApplication.run(AhnenbaumShellStarter.class, args);
	}

}
