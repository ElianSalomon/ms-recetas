package recetas.salud.elian.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import recetas.salud.elian.dto.PacienteDetailsDTO;

@FeignClient(name = "ms-pacientes")
public interface PacienteFeignClient {

    @GetMapping("/api/pacientes/{id}")
    PacienteDetailsDTO getPacienteById(@PathVariable("id") Long pacienteId);
}
