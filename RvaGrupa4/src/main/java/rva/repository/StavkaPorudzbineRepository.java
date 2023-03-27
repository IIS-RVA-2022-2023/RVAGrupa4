package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rva.models.*;


public interface StavkaPorudzbineRepository extends JpaRepository <StavkaPorudzbine, Integer>{

	List<StavkaPorudzbine> findByPorudzbina(Porudzbina porudzbina);
	List<StavkaPorudzbine> findByCenaLessThanOrderById(double cena);
	
	@Query(value = "select coalesce(max(redni_broj)+1, 1) from stavka_porudzbine where porudzbina = ?1", nativeQuery = true)
    Integer nextRBr(int porudzbinaId);

}
