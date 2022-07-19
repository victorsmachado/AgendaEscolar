package br.gov.sp.fatec.springbootapp;

import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.gov.sp.fatec.springbootapp.service.FilesStorageService;

@SpringBootApplication
public class SpringBootAppApplication implements CommandLineRunner {


	@Resource
  	FilesStorageService storageService;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
	  storageService.deleteAll();
	  storageService.init();
	}

}
