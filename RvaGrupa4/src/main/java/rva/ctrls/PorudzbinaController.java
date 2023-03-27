package rva.ctrls;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.*;
import rva.services.PorudzbinaService;

@CrossOrigin
@RestController
public class PorudzbinaController {

	@Autowired
	private PorudzbinaService porudzbinaService;


	@GetMapping("porudzbina")
	public ResponseEntity<List<Porudzbina>> getAll() {
		List<Porudzbina> porudzbinas = porudzbinaService.getAll();
		return new ResponseEntity<>(porudzbinas, HttpStatus.OK);
	}

	@GetMapping("porudzbina/{id}")
	public ResponseEntity<Porudzbina> getOne(@PathVariable("id") int id) {
		if (porudzbinaService.existsById(id)) {
			Optional<Porudzbina> porudzbinaOpt = porudzbinaService.findById(id);
			return new ResponseEntity<>(porudzbinaOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("placenePorudzbine")
	public ResponseEntity<List<Porudzbina>> placenePorudzbine() {
		List<Porudzbina> porudzbinas = porudzbinaService.findByPlacenoTrue();
		return new ResponseEntity<>(porudzbinas, HttpStatus.OK);
	}

	@PostMapping("porudzbina")
    public ResponseEntity<?> addPorudbina(@RequestBody Porudzbina porudzbina) {
    	if(!porudzbinaService.existsById(porudzbina.getId())) {
    		Porudzbina savedPorudzbina = porudzbinaService.save(porudzbina);
            return ResponseEntity.status(HttpStatus.OK).body(savedPorudzbina);
    	}else {
    		return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource with the same ID already exists");
    	}       
    }

	@PutMapping(value = "porudzbina/{id}")
	public ResponseEntity<Porudzbina> updatePorudzbina(@RequestBody Porudzbina porudzbina,
			@PathVariable("id") int id) {
		if (porudzbinaService.existsById(id)) {
			porudzbina.setId(id);
			Porudzbina savedPorudzbina = porudzbinaService.save(porudzbina);
			return ResponseEntity.ok().body(savedPorudzbina);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("porudzbina/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
    	if(porudzbinaService.existsById(id)) {
    		porudzbinaService.deleteById(id);
    		return ResponseEntity.ok("Resource with an id:" + id + " has been deleted");
    	}else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested resource has not been found");
    	}
    }
}
