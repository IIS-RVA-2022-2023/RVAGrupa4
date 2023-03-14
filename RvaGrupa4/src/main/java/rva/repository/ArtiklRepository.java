package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.models.Artikl;

public interface ArtiklRepository extends JpaRepository<Artikl, Integer>{
	
	List<Artikl> findByNazivContainingIgnoreCase(String naziv);
	
	//1. nacin
	/*@Query(value="select * from artikl where lower(naziv) like ?1%", nativeQuery = true)
	List<Artikl> getByPocetak(String pocetakNaziva);*/
	
	//2. nacin
	@Query(value="select * from artikl where lower(naziv) like :pocetak%", nativeQuery = true)
	List<Artikl> getByPocetak(@Param("pocetak") String pocetakNaziva);
}
