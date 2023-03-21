package rva.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Porudzbina implements Serializable{

	private static final long serialVersionUID=1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PORUDZBINA_ID_GENERATOR")
	@SequenceGenerator(name="PORUDZBINA_ID_GENERATOR", sequenceName = "PORUDZBINA_SEQ", allocationSize = 1)
	private int id;
	
	private Date datum;
	
	private Date isporuceno;
	
	private double iznos;
	
	private boolean placeno;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="dobavljac")
	private Dobavljac dobavljac;
	
	@JsonIgnore
	@OneToMany(mappedBy="porudzbina")
	private List<StavkaPorudzbine> stavkePorudzbine;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Date getIsporuceno() {
		return isporuceno;
	}

	public void setIsporuceno(Date isporuceno) {
		this.isporuceno = isporuceno;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public boolean isPlaceno() {
		return placeno;
	}

	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}

	public Dobavljac getDobavljac() {
		return dobavljac;
	}

	public void setDobavljac(Dobavljac dobavljac) {
		this.dobavljac = dobavljac;
	}
	
	
	
	
}
