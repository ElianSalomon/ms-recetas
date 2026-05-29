package recetas.salud.elian.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import recetas.salud.elian.dto.CitaDetailsDTO;

@FeignClient(name = "ms-citas")
public interface CitaFeignClient {

    @GetMapping("/api/citas/{id}")
    CitaDetailsDTO getCitaById(@PathVariable("id") Long citaId);
}
