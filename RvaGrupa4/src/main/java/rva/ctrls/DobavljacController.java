package rva.ctrls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.*;
import rva.services.DobavljacService;

@CrossOrigin
@RestController
public class DobavljacController {

	 	@Autowired
	    private DobavljacService dobavljacService;

	    @GetMapping("dobavljac")
	    public ResponseEntity<List<Dobavljac>> getAll() {
	        List<Dobavljac> dobavljacs = dobavljacService.getAll();
	        return new ResponseEntity<>(dobavljacs, HttpStatus.OK);
	    }

	    @GetMapping("dobavljac/{id}")
	    public ResponseEntity<Dobavljac> getOne(@PathVariable("id") int id) {
	        if (dobavljacService.findById(id).isPresent()) {
	            Optional<Dobavljac> dobavljacOpt = dobavljacService.findById(id);
	            return new ResponseEntity<>(dobavljacOpt.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    }

	    
	    @GetMapping("dobavljac/naziv/{naziv}")
	    public ResponseEntity<List<Dobavljac>> getByNaziv(@PathVariable("naziv") String naziv) {
	        List<Dobavljac> dobavljacs = dobavljacService.findByNazivContainingIgnoreCase(naziv);
	        return new ResponseEntity<>(dobavljacs, HttpStatus.OK);
	    }

	    @PostMapping("dobavljac")
	    public ResponseEntity<?> addArtikl(@RequestBody Dobavljac dobavljac) {
	    	if(!dobavljacService.existsById(dobavljac.getId())) {
	    		Dobavljac savedDobavljac = dobavljacService.save(dobavljac);
	            return ResponseEntity.status(HttpStatus.OK).body(savedDobavljac);
	    	}else {
	    		return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource with the same ID already exists");
	    	}       
	    }

	    
	    @PutMapping(value = "dobavljac/{id}")
	    public ResponseEntity<Dobavljac> updateDobavljac(@RequestBody Dobavljac dobavljac, @PathVariable("id") int id) {
	        if (dobavljacService.existsById(id)) {
	            dobavljac.setId(id);
	            Dobavljac savedDobavljac = dobavljacService.save(dobavljac);
	            return ResponseEntity.ok().body(savedDobavljac);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    @DeleteMapping("dobavljac/{id}")
	    public ResponseEntity<?> delete(@PathVariable int id) {
	    	if(dobavljacService.existsById(id)) {
	    		dobavljacService.deleteById(id);
	    		return ResponseEntity.ok("Resource with an id:" + id + " has been deleted");
	    	}else {
	    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested resource has not been found");
	    	}
	    }
	    
}