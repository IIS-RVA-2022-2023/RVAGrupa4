package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.repository.StavkaPorudzbineRepository;
import rva.models.*;

@Service
public class StavkaPorudzbineService {
	@Autowired
	private StavkaPorudzbineRepository stavka;

	public List<StavkaPorudzbine> getAll() {
		return stavka.findAll();
	}

	public Optional<StavkaPorudzbine> findById(int id) {
		return stavka.findById(id);
	}

	public StavkaPorudzbine save(StavkaPorudzbine stavkaPorudzbine) {
		return stavka.save(stavkaPorudzbine);
	}

	public boolean existsById(int id) {
		if (stavka.findById(id) != null) {
			return true;
		} else {
			return false;
		}
	}

	public void deleteById(int id) {
		stavka.deleteById(id);
	}

	
	public List<StavkaPorudzbine> findByPorudzbina(Porudzbina porudzbina) {
		return stavka.findByPorudzbina(porudzbina);
	}
	

	
	public List<StavkaPorudzbine> findByCenaLessThanOrderById(double cena) {
		return stavka.findByCenaLessThanOrderById(cena); }

	
	public Integer nextRBr(int porudzbinaId) { return
	 stavka.nextRBr(porudzbinaId); }
}