package recetas.salud.elian.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import recetas.salud.elian.dto.RecetaDTO;
import recetas.salud.elian.services.RecetaService;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    private RecetaService recetaService;

    @PostMapping
    public ResponseEntity<RecetaDTO> createReceta(@RequestBody RecetaDTO recetaDto) {
        RecetaDTO savedReceta = recetaService.createReceta(recetaDto);
        return new ResponseEntity<>(savedReceta, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<RecetaDTO> getRecetaById(@PathVariable("id") Long recetaId) {
        RecetaDTO recetaDto = recetaService.getRecetaById(recetaId);
        if (recetaDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(recetaDto);
    }

    @GetMapping("/activas")
    public ResponseEntity<List<RecetaDTO>> getRecetasActivas() {
        return ResponseEntity.ok(recetaService.getRecetasActivas());
    }

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<RecetaDTO>> getRecetasByPacienteId(@PathVariable("idPaciente") Long idPaciente) {
        return ResponseEntity.ok(recetaService.getRecetasByPacienteId(idPaciente));
    }

    @GetMapping("/cita/{idCita}")
    public ResponseEntity<List<RecetaDTO>> getRecetasByCitaId(@PathVariable("idCita") Long idCita) {
        return ResponseEntity.ok(recetaService.getRecetasByCitaId(idCita));
    }

    @PutMapping("{id}")
    public ResponseEntity<RecetaDTO> updateReceta(@PathVariable("id") Long recetaId, @RequestBody RecetaDTO updateReceta) {
        RecetaDTO recetaDto = recetaService.updateReceta(recetaId, updateReceta);
        if (recetaDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(recetaDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> desactivarReceta(@PathVariable("id") Long recetaId) {
        recetaService.desactivarReceta(recetaId);
        return ResponseEntity.ok("Receta desactivada");
    }
}
