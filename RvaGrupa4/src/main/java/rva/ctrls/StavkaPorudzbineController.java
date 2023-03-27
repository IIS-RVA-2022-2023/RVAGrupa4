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

import rva.services.*;
import rva.models.*;

@CrossOrigin
@RestController
public class StavkaPorudzbineController {

	
	@Autowired
    private StavkaPorudzbineService stavkaPorudzbineService;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping("stavkaPorudzbine")
    public ResponseEntity<List<StavkaPorudzbine>> getAll() {
        List<StavkaPorudzbine> stavkaPorudzbines = stavkaPorudzbineService.getAll();
        return new ResponseEntity<>(stavkaPorudzbines, HttpStatus.OK);
    }

    @GetMapping("stavkaPorudzbine/{id}")
    public ResponseEntity<StavkaPorudzbine> getOne(@PathVariable("id") int id) {
        if (stavkaPorudzbineService.findById(id).isPresent()) {
            Optional<StavkaPorudzbine> stavkaPorudzbineOpt = stavkaPorudzbineService.findById(id);
            return new ResponseEntity<>(stavkaPorudzbineOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("stavkaPorudzbine/{id}")
    public ResponseEntity<StavkaPorudzbine> updateOne(@RequestBody StavkaPorudzbine stavkaPorudzbine,
            @PathVariable("id") int id) {
        if (!stavkaPorudzbineService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        stavkaPorudzbine.setId(id);
        StavkaPorudzbine savedStavkaPorudzbine = stavkaPorudzbineService.save(stavkaPorudzbine);
        return ResponseEntity.ok().body(savedStavkaPorudzbine);
    }

    @DeleteMapping("stavkaPorudzbine/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
    	if(stavkaPorudzbineService.existsById(id)) {
    		stavkaPorudzbineService.deleteById(id);
    		return ResponseEntity.ok("Resource with an id:" + id + " has been deleted");
    	}else {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested resource has not been found");
    	}
    }
    
    @GetMapping("stavkeZaPorudzbinu/{id}")
    public ResponseEntity<?> getAllForPorudzbina(@PathVariable("id") int id) {
        Optional<Porudzbina> porudzbinaOpt = porudzbinaService.findById(id);
        if (porudzbinaOpt.isPresent()) {
            List<StavkaPorudzbine> stavkaPorudzbines = stavkaPorudzbineService
            		.findByPorudzbina(porudzbinaOpt.get());            
            if(stavkaPorudzbines.isEmpty()) {
            	return new ResponseEntity<>("Stavke za porudzbinu nisu pronadjene",
            			HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(stavkaPorudzbines, HttpStatus.OK);
        }
        return new ResponseEntity<>("Porudzbina nije pronadjena", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "stavkaPorudzbineCena/{cena}")
    public ResponseEntity<List<StavkaPorudzbine>> getStavkaPorudzbineCena(@PathVariable("cena") double cena) {
        List<StavkaPorudzbine> stavkaPorudzbines = stavkaPorudzbineService.findByCenaLessThanOrderById(cena);
        return new ResponseEntity<>(stavkaPorudzbines, HttpStatus.OK);

    }

    @PostMapping("stavkaPorudzbine")
    public ResponseEntity<?> addOne(@RequestBody StavkaPorudzbine stavkaPorudzbine) {
        if (!porudzbinaService.existsById(stavkaPorudzbine.getPorudzbina().getId())) {
            return new ResponseEntity<>("Porudzbina - not found",HttpStatus.NOT_FOUND);
        }      
        stavkaPorudzbine.setRedniBroj(stavkaPorudzbineService
        		.nextRBr(stavkaPorudzbine.getPorudzbina().getId()));
        StavkaPorudzbine savedStavkaPorudzbine = stavkaPorudzbineService.save(stavkaPorudzbine);
        return ResponseEntity.status(HttpStatus.OK).body(savedStavkaPorudzbine);
    }

}
