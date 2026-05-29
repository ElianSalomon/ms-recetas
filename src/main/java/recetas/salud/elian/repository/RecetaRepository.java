package recetas.salud.elian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import recetas.salud.elian.entity.Receta;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByActivaTrue();

    List<Receta> findByIdPaciente(Long idPaciente);

    List<Receta> findByIdCita(Long idCita);
}
