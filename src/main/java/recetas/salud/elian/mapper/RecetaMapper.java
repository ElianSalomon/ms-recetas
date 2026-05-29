package recetas.salud.elian.mapper;

import recetas.salud.elian.dto.RecetaDTO;
import recetas.salud.elian.entity.Receta;

public class RecetaMapper {

    public static RecetaDTO mapToRecetaDTO(Receta receta) {
        RecetaDTO dto = new RecetaDTO();

        dto.setIdReceta(receta.getIdReceta());
        dto.setIdCita(receta.getIdCita());
        dto.setIdPaciente(receta.getIdPaciente());
        dto.setDiagnostico(receta.getDiagnostico());
        dto.setMedicamentos(receta.getMedicamentos());
        dto.setActiva(receta.getActiva());

        return dto;
    }

    public static Receta mapToReceta(RecetaDTO recetaDTO) {
        Receta receta = new Receta();

        receta.setIdReceta(recetaDTO.getIdReceta());
        receta.setIdCita(recetaDTO.getIdCita());
        receta.setIdPaciente(recetaDTO.getIdPaciente());
        receta.setDiagnostico(recetaDTO.getDiagnostico());
        receta.setMedicamentos(recetaDTO.getMedicamentos());
        receta.setActiva(recetaDTO.getActiva());

        return receta;
    }
}
