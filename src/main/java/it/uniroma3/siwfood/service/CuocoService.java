package it.uniroma3.siwfood.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.User;
import it.uniroma3.siwfood.repository.CuocoRepository;

@Service
public class CuocoService {
	
	@Autowired
	protected CuocoRepository cuocoRepository;

	
	public Cuoco findById(long id) {
		return cuocoRepository.findById(id).get();
	}
	public Iterable<Cuoco> findAll(){
		return cuocoRepository.findAll();
	}
	
	@Transactional
    public Cuoco getCuoco(Long id) {
        Optional<Cuoco> result = this.cuocoRepository.findById(id);
        return result.orElse(null);
    }
	
	@Transactional
    public Cuoco saveCuoco(Cuoco cuoco) {
        return this.cuocoRepository.save(cuoco);
    }

    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<Cuoco> getAllCuochi() {
        List<Cuoco> result = new ArrayList<>();
        Iterable<Cuoco> iterable = this.cuocoRepository.findAll();
        for(Cuoco cuoco : iterable)
            result.add(cuoco);
        return result;
    }

}
