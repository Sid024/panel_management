package com.zensar.pm.panel;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zensar.pm.panel.export.FileExporter;

@SpringBootApplication
public class PanelManagementRestApiPmDec2022Application {

	public static void main(String[] args) {
		SpringApplication.run(PanelManagementRestApiPmDec2022Application.class, args);
	}
	
	@Bean
	public FileExporter fileExport() {
		return new FileExporter();
	}
	
	
	

}
