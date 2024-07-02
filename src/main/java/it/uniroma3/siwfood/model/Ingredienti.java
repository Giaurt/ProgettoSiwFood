package it.uniroma3.siwfood.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ingredienti")
public class Ingredienti {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String quantità;
	@ManyToMany(mappedBy="ingredienti")
	private List<Ricetta> ricette;
	
	
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
	public String getQuantità() {
		return quantità;
	}
	public void setQuantità(String quantità) {
		this.quantità = quantità;
	}
	public List<Ricetta> getRicette() {
		return ricette;
	}
	public void setRicette(List<Ricetta> ricette) {
		this.ricette = ricette;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredienti other = (Ingredienti) obj;
		return Objects.equals(nome, other.nome);
	}
	
	
	
	

	
}
