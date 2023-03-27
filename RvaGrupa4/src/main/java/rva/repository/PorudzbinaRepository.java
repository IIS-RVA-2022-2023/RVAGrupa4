package rva.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.models.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository <Porudzbina, Integer>{
	
	List<Porudzbina> findByPlacenoTrue();

}