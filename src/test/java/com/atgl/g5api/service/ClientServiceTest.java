package com.atgl.g5api.service;

import com.atgl.g5api.model.Client;
import com.atgl.g5api.model.Compte;
import com.atgl.g5api.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    ClientRepository repository;
    @Mock
    CompteService compteService;

    @InjectMocks
    ClientService service;

    @Test
    void getAllClientsTest(){
        Client client = new Client();
        client.setId(1L);
        client.setNom("Diallo");
        client.setPrenom("Mohamed");
        client.setTelephone("123-123-123a");

        Client client2 = new Client();
        client2.setId(2L);
        client2.setNom("Faye");
        client2.setPrenom("Mansour");
        client2.setTelephone("123-123-123a");
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(client);
        clients.add(client2);

        when(repository.findAll()).thenReturn(clients);
        Iterable <Client> foundClients= service.getClients();
        assertEquals(clients,foundClients);
        verify(repository,times(1)).findAll();
    }
    @Test
    void getClientByIdTest(){
        Client client = new Client();
        client.setId(5L);
        client.setNom("Diallo");
        client.setPrenom("Mohamed");
        client.setTelephone("123-123-123a");

        when(repository.findById(client.getId())).thenReturn(Optional.of(client));
        Optional<Client> foundClient = service.getClientById(client.getId());
        if (foundClient.isPresent())
        {
            Client clientFound = foundClient.get();
            assertEquals(client,clientFound);
        }
        verify(repository,times(1)).findById(client.getId());
    }
    @Test
    void createClientTest(){
        Client client = new Client();
        client.setId(5L);
        client.setNom("Diallo");
        client.setPrenom("Mohamed");
        client.setTelephone("123-123-123a");

        when(repository.save(client)).thenReturn(client);
        assertEquals(service.saveClient(client),client);

        verify(repository,times(1)).save(client);
    }
    @Test
    void deleteClient(){
        Client client = new Client();

        client.setId(5L);
        client.setNom("Diallo");
        client.setPrenom("Mohamed");
        client.setTelephone("123-123-123a");

        service.deleteClient(5L);
        verify(compteService,times(1)).deleteCompte(client.getId());
        verify(repository,times(1)).deleteById(client.getId());
    }

    @Test
    void crediterTest(){
        service.crediterCompte(1,2000);

        verify(compteService,times(1)).crediterCompte(1,2000);
    }

    @Test
    void EnvoieTest(){
        Client client=new Client();
        client.setId(1L);
        when(service.getClientById(1L)).thenReturn(Optional.of(client));
        service.EnvoieClient(1,2000,3);

        verify(compteService,times(0)).crediterCompte(1,2000);
    }

}
