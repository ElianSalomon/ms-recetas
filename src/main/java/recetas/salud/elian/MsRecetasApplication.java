package recetas.salud.elian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsRecetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRecetasApplication.class, args);
	}

}
