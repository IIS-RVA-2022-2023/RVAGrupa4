package rva.ctrls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Artikl;
import rva.services.ArtiklService;

@RestController
public class ArtiklController {
	
	@Autowired
	private ArtiklService artiklService;
	
	@GetMapping("artikl")
	public ResponseEntity<?> getAllArtikl(){
		List<Artikl> artikli = artiklService.getAllArtikl();
		if(artikli.isEmpty())
			return new ResponseEntity<>(
			          "Artikli - not found", 
			          HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(artikli, HttpStatus.OK); 
	}
	
	@GetMapping("artikl/{id}")
	public  ResponseEntity<?> getArtiklById(@PathVariable("id") int idArtikl){		
		if(artiklService.existsById(idArtikl)) {
			Optional<Artikl> artikl = artiklService.getArtiklById(idArtikl);
			return new ResponseEntity<>(artikl, HttpStatus.OK);
		}
		return new ResponseEntity<>(
		          "Artikli with requested id "+ idArtikl +" not found", 
		          HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("artiklNaziv/{naziv}")
	public  ResponseEntity<?> getArtiklByNaziv(@PathVariable("naziv") String nazivArtikla){
		List<Artikl> artikli = artiklService.getArtiklByNaziv(nazivArtikla);
		if(artikli.isEmpty())
			return new ResponseEntity<>(
			          "Artikli with that naziv - not found", 
			          HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(artikli); 
	}
	
	@GetMapping("artiklNazivPocetak/{pocetakNaziva}")
	public  ResponseEntity<?> getArtiklByPocetnoSlovo(@PathVariable("pocetakNaziva") String pocetakNaziva){
		List<Artikl> artikli = artiklService.getArtiklByPocetnoSlovo(pocetakNaziva);
		if(artikli.isEmpty())
			return new ResponseEntity<>(
			          "Artikli with that pocetakNaziva - not found", 
			          HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(artikli);
	}
	
	@PostMapping("artikl")
	public ResponseEntity<?> addArtikl(@RequestBody Artikl artikl){
		if(artiklService.existsById(artikl.getId())) {
			return new ResponseEntity<>(
			          "Artikl with that id already exists", 
			          HttpStatus.CONFLICT);
		}
		Artikl savedArtikl = artiklService.addArtikl(artikl);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(savedArtikl);
	}
	
	@PutMapping("artikl/{id}")
	public ResponseEntity<?> updateArtikl(@RequestBody Artikl artikl, @PathVariable("id") int idArtikl){
		artikl.setId(idArtikl);
		if(!artiklService.existsById(artikl.getId())) {
			return new ResponseEntity<>(
			          "Artikl with that id does not exist", 
			          HttpStatus.CONFLICT);
		}
		Artikl savedArtikl = artiklService.addArtikl(artikl);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(savedArtikl);
	}
	
	@DeleteMapping("artikl/{id}")
	public ResponseEntity<String> deleteArtikl(@PathVariable("id") int idArtikl){
		if(!artiklService.existsById(idArtikl)) {
			return new ResponseEntity<>(
			          "Artikl with that id does not exist", 
			          HttpStatus.CONFLICT);
		}
		artiklService.deleteArtikl(idArtikl);
		return ResponseEntity.status(HttpStatus.OK)
		        .body("Artikl has been deleted");
	}
	
}
