package com.atgl.g5api.service;

import com.atgl.g5api.model.Client;
import com.atgl.g5api.model.Compte;
import com.atgl.g5api.repository.ClientRepository;
import com.atgl.g5api.repository.CompteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    private CompteService compte;

    public ClientService(ClientRepository repository,CompteService compte) {
        this.repository = repository;
        this.compte = compte;
    }

    public Iterable<Client> getClients(){
        return repository.findAll();
    }

    public Optional<Client> getClientById(Long id){
        return repository.findById(id);
    }

    public Client saveClient(Client client){
        Client c2 = repository.save(client);
        compte.createCompte(c2.getId());
        return c2;
    }

    public void crediterCompte(int id,int montant){
        compte.crediterCompte(id,montant);
    }

    public void retirerCompte(int id,int montant){
        compte.retirerCompte(id,montant);
    }

    //Cette methode peut servir dans les 2 sens. Dans le cas d'untransfert et d'une annulation,....
    public void EnvoieClient(int envoyeur,int montant,long recepteur)
    {
        if (getClientById(recepteur).isPresent())
        {
            compte.crediterCompte(envoyeur,montant);

        }
    }
    public void AnnulationClient(int envoyeur,int montant,long recepteur)
    {
        if (getClientById(recepteur).isPresent())
        {

            compte.retirerCompte((int) recepteur,montant);
            compte.crediterCompte(envoyeur,montant);        }
    }

    public Optional<Compte>getClientCompte(int id){return compte.getCompte(id);}

    public void deleteClient(Long id){
        compte.deleteCompte(id);
        repository.deleteById(id);
    }
}
