package recetas.salud.elian.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import recetas.salud.elian.dto.CitaDetailsDTO;
import recetas.salud.elian.dto.PacienteDetailsDTO;
import recetas.salud.elian.dto.RecetaDTO;
import recetas.salud.elian.entity.Receta;
import recetas.salud.elian.feignclients.CitaFeignClient;
import recetas.salud.elian.feignclients.PacienteFeignClient;
import recetas.salud.elian.mapper.RecetaMapper;
import recetas.salud.elian.repository.RecetaRepository;
import recetas.salud.elian.services.RecetaService;

@Service
@AllArgsConstructor
public class RecetaServicesImpl implements RecetaService {

    private RecetaRepository recetaRepository;
    private CitaFeignClient citaFeignClient;
    private PacienteFeignClient pacienteFeignClient;

    @Override
    public RecetaDTO createReceta(RecetaDTO recetaDto) {
        CitaDetailsDTO cita = validarCita(recetaDto.getIdCita());

        if (!cita.getIdPaciente().equals(recetaDto.getIdPaciente())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cita no corresponde al paciente enviado");
        }

        PacienteDetailsDTO paciente = validarPaciente(recetaDto.getIdPaciente());

        Receta receta = RecetaMapper.mapToReceta(recetaDto);
        receta.setActiva(true);

        Receta savedReceta = recetaRepository.save(receta);
        return enrichReceta(savedReceta, cita, paciente);
    }

    @Override
    public RecetaDTO getRecetaById(Long recetaId) {
        Receta receta = recetaRepository.findById(recetaId).orElse(null);
        return receta == null ? null : enrichReceta(receta);
    }

    @Override
    public List<RecetaDTO> getRecetasActivas() {
        return recetaRepository.findByActivaTrue().stream()
                .map(this::enrichReceta)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecetaDTO> getRecetasByPacienteId(Long idPaciente) {
        validarPaciente(idPaciente);
        return recetaRepository.findByIdPaciente(idPaciente).stream()
                .map(this::enrichReceta)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecetaDTO> getRecetasByCitaId(Long idCita) {
        validarCita(idCita);
        return recetaRepository.findByIdCita(idCita).stream()
                .map(this::enrichReceta)
                .collect(Collectors.toList());
    }

    @Override
    public RecetaDTO updateReceta(Long recetaId, RecetaDTO updateReceta) {
        Receta receta = recetaRepository.findById(recetaId).orElse(null);

        if (receta == null) {
            return null;
        }

        CitaDetailsDTO cita = validarCita(updateReceta.getIdCita());

        if (!cita.getIdPaciente().equals(updateReceta.getIdPaciente())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cita no corresponde al paciente enviado");
        }

        PacienteDetailsDTO paciente = validarPaciente(updateReceta.getIdPaciente());

        receta.setIdCita(updateReceta.getIdCita());
        receta.setIdPaciente(updateReceta.getIdPaciente());
        receta.setDiagnostico(updateReceta.getDiagnostico());
        receta.setMedicamentos(updateReceta.getMedicamentos());

        Receta updatedReceta = recetaRepository.save(receta);
        return enrichReceta(updatedReceta, cita, paciente);
    }

    @Override
    public void desactivarReceta(Long recetaId) {
        Receta receta = recetaRepository.findById(recetaId).orElse(null);

        if (receta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receta no encontrada");
        }

        receta.setActiva(false);
        recetaRepository.save(receta);
    }

    private CitaDetailsDTO validarCita(Long idCita) {
        CitaDetailsDTO cita = citaFeignClient.getCitaById(idCita);

        if (cita == null || Boolean.FALSE.equals(cita.getActiva())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se puede emitir una receta sin una consulta medica valida");
        }

        if (!"PROGRAMADA".equalsIgnoreCase(cita.getEstado()) && !"COMPLETA".equalsIgnoreCase(cita.getEstado())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cita no tiene un estado valido para emitir receta");
        }

        return cita;
    }

    private PacienteDetailsDTO validarPaciente(Long idPaciente) {
        PacienteDetailsDTO paciente = pacienteFeignClient.getPacienteById(idPaciente);

        if (paciente == null || Boolean.FALSE.equals(paciente.getActivo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paciente no disponible");
        }

        return paciente;
    }

    private RecetaDTO enrichReceta(Receta receta) {
        CitaDetailsDTO cita = citaFeignClient.getCitaById(receta.getIdCita());
        PacienteDetailsDTO paciente = pacienteFeignClient.getPacienteById(receta.getIdPaciente());
        return enrichReceta(receta, cita, paciente);
    }

    private RecetaDTO enrichReceta(Receta receta, CitaDetailsDTO cita, PacienteDetailsDTO paciente) {
        RecetaDTO dto = RecetaMapper.mapToRecetaDTO(receta);
        dto.setCita(cita);
        dto.setPaciente(paciente);
        return dto;
    }
}
