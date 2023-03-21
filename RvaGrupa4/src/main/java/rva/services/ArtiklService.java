package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Artikl;
import rva.repository.ArtiklRepository;

@Service
public class ArtiklService {

	@Autowired
	private ArtiklRepository artiklRepository;
	
	public List<Artikl> getAllArtikl(){
		return artiklRepository.findAll();
	}
	
	/*public Artikl getArtiklById(int id){
		return artiklRepository.getById(id);
	}*/
	public Optional<Artikl> getArtiklById(int id){
		return artiklRepository.findById(id);
	}

	public List<Artikl> getArtiklByNaziv(String nazivArtikla) {
		return artiklRepository.findByNazivContainingIgnoreCase(nazivArtikla);
	}

	public List<Artikl> getArtiklByPocetnoSlovo(String pocetakNaziva) {
		String pocetakNazivaMalimSlovom = pocetakNaziva.toLowerCase();
		return artiklRepository.getByPocetak(pocetakNazivaMalimSlovom);
	}
	
	public boolean existsById(int id) {
		return getArtiklById(id).isPresent();
	}
	
	public Artikl addArtikl(Artikl artikl) {
		return artiklRepository.save(artikl);
	}
	
	public void deleteArtikl(int id) {
		artiklRepository.deleteById(id);
	}
}
