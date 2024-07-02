package it.uniroma3.siwfood.model;


import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Ricetta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String descrizione;
	private String tipo;
	@OneToMany
	private List<Ingredienti> ingredienti;
	private String cuoco;
	
	@OneToMany
	private List<Image> imageList;
	
	@OneToOne
	private Image defaultImage;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public List<Ingredienti> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingredienti> ingredienti) {
		this.ingredienti = ingredienti;
	}
	public String getCuoco() {
		return cuoco;
	}
	public void setCuoco(String cuoco) {
		this.cuoco = cuoco;
	}
	
	public List<Image> getImageList() {
		return imageList;
	}
	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}
	public Image getDefaultImage() {
		return defaultImage;
	}
	public void setDefaultImage(Image defaultImage) {
		this.defaultImage = defaultImage;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ricetta other = (Ricetta) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
}
