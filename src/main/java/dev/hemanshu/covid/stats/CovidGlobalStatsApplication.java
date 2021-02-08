package dev.hemanshu.covid.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class CovidGlobalStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidGlobalStatsApplication.class, args);
	}

}
