package rva.ctrls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Artikl;
import rva.services.ArtiklService;

@RestController
public class ArtiklController {
	
	@Autowired
	private ArtiklService artiklService;
	
	@GetMapping("artikl")
	public List<Artikl> getAllArtikl(){
		return artiklService.getAllArtikl();
	}
	
	@GetMapping("artikl/{id}")
	public  Optional<Artikl> getArtiklById(@PathVariable("id") int idArtikl){
		return artiklService.getArtiklById(idArtikl);
	}
	
	@GetMapping("artiklNaziv/{naziv}")
	public  List<Artikl> getArtiklByNaziv(@PathVariable("naziv") String nazivArtikla){
		return artiklService.getArtiklByNaziv(nazivArtikla);
	}
	
	@GetMapping("artiklNazivPocetak/{pocetakNaziva}")
	public  List<Artikl> getArtiklByPocetnoSlovo(@PathVariable("pocetakNaziva") String pocetakNaziva){
		return artiklService.getArtiklByPocetnoSlovo(pocetakNaziva);
	}
}
