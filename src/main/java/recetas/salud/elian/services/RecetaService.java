package recetas.salud.elian.services;

import java.util.List;

import recetas.salud.elian.dto.RecetaDTO;

public interface RecetaService {

    RecetaDTO createReceta(RecetaDTO recetaDto);

    RecetaDTO getRecetaById(Long recetaId);

    List<RecetaDTO> getRecetasActivas();

    List<RecetaDTO> getRecetasByPacienteId(Long idPaciente);

    List<RecetaDTO> getRecetasByCitaId(Long idCita);

    RecetaDTO updateReceta(Long recetaId, RecetaDTO updateReceta);

    void desactivarReceta(Long recetaId);
}
