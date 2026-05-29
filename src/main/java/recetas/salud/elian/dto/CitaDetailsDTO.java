package recetas.salud.elian.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitaDetailsDTO {

    private Long idCita;
    private Long idPaciente;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String motivoConsulta;
    private String estado;
    private Boolean activa;
}
