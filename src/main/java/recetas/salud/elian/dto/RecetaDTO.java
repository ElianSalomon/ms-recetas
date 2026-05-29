package recetas.salud.elian.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecetaDTO {

    private Long idReceta;
    private Long idCita;
    private Long idPaciente;
    private String diagnostico;
    private String medicamentos;
    private Boolean activa;
    private CitaDetailsDTO cita;
    private PacienteDetailsDTO paciente;
}
