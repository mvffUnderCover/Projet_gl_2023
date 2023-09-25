package com.atgl.g5api.service;

import com.atgl.g5api.model.Compte;
import com.atgl.g5api.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompteService {
    @Autowired
    CompteRepository repository;

    public Compte createCompte(Long owner){
        long count = repository.count();
        Compte compte = new Compte();
        compte.setId(count+1);
        compte.setOwner(owner);
        return repository.save(compte);
    }


    public void crediterCompte(int id,int montant){
        Optional<Compte> c = Optional.ofNullable(repository.findByOwner(id));
        if (c.isPresent()){
            Compte compte = c.get();
            compte.setSolde(compte.getSolde()+montant);
            repository.save(compte);
        }
    }
    public void retirerCompte(int id,int montant){
        Optional<Compte> c = Optional.ofNullable(repository.findByOwner(id));
        if (c.isPresent()){
            Compte compte = c.get();
            compte.setSolde(compte.getSolde()-montant);
            repository.save(compte);
        }
    }

    public Optional<Compte> getCompte(int id){
        return Optional.ofNullable(repository.findByOwner(id));
    }

    public void deleteCompte(long owner){
        Compte compte = repository.findByOwner(owner);
        repository.delete(compte);
    }

}
