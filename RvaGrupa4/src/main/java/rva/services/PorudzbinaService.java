package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Porudzbina;
import rva.repository.PorudzbinaRepository;

@Service
public class PorudzbinaService {

	@Autowired
	private PorudzbinaRepository porudzbinaRepository;
	
	public List<Porudzbina> getAll(){
		return porudzbinaRepository.findAll();
	}
	
	public Optional<Porudzbina> findById(int id) {
		return porudzbinaRepository.findById(id);
	}
	
	public List<Porudzbina> findByPlacenoTrue() {
        return porudzbinaRepository.findByPlacenoTrue();
    }
	
	public Porudzbina save(Porudzbina porudzbina) {
		return porudzbinaRepository.save(porudzbina);
	}
	
	public boolean existsById(int id) {
		if(porudzbinaRepository.findById(id).isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	public void deleteById(int id) {
		porudzbinaRepository.deleteById(id);
	}
}
