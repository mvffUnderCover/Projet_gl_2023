package com.atgl.g5api.controller;

import com.atgl.g5api.model.Client;
import com.atgl.g5api.model.Compte;
import com.atgl.g5api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping("/clients")
    public Iterable<Client> getClients(){
        return service.getClients();
    }

    @GetMapping("/clients/{id}")
    public Optional<Client> getClientById(@PathVariable("id") String id){
        return service.getClientById(Long.valueOf(id));
    }

    @GetMapping("/clients/{id}/compte")
    public Optional<Compte> getClientCompte(@PathVariable("id") int id){
        return service.getClientCompte(id);
    }

    @PostMapping(path="/clients")
    public Client saveClient(@RequestBody Client c){return service.saveClient(c);}

    @PutMapping("clients/depot/{id}")
    public void creditClient(@PathVariable("id") int id,@RequestBody Compte updatedCompte){
        service.crediterCompte(id,updatedCompte.getSolde());
    }
    @PutMapping("clients/retrait/{id}")
    public void withdrawClient(@PathVariable("id") int id,@RequestBody Compte updatedCompte){
        service.retirerCompte(id,updatedCompte.getSolde());
    }

    @PutMapping("clients/{id}/envoie")
    public void sendClient(@PathVariable("id") int id,@RequestBody Compte updatedCompte, @PathVariable("id") int owner){
        service.EnvoieClient(id,updatedCompte.getSolde(), updatedCompte.getOwner());
    }
    @PutMapping("clients/{id}/annuler")
    public void cancelClient(@PathVariable("id") int id,@RequestBody Compte updatedCompte){
        service.AnnulationClient(id,updatedCompte.getSolde(),updatedCompte.getOwner());
    }
    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable("id") String id){service.deleteClient(Long.valueOf(id));}
}
