package recetas.salud.elian.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDetailsDTO {

    private Long idPaciente;
    private String nombre;
    private String apellidos;
    private String curp;
    private LocalDate fechaNacimiento;
    private String tipoSangre;
    private String telefono;
    private String email;
    private Boolean activo;
}
