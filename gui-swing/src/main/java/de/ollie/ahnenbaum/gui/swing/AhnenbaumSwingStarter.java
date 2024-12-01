package de.ollie.ahnenbaum.gui.swing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Starter class for the Ahnenbaum swing application.
 * 
 * @author ollie (01.12.2024)
 */
@SpringBootApplication
@ComponentScan("de.ollie")
public class AhnenbaumSwingStarter {

	public static void main(String[] args) {
		SpringApplication.run(AhnenbaumSwingStarter.class, args);
	}

}
