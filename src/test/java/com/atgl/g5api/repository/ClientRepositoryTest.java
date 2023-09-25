package com.atgl.g5api.repository;

import com.atgl.g5api.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientRepositoryTest {

    @MockBean
    private ClientRepository repository;

    @Test
    void getAllClientsTest(){
        Client client = new Client();
        client.setId(5L);
        client.setNom("Diallo");
        client.setPrenom("Mohamed");
        client.setTelephone("123-123-123a");

        Client client2 = new Client();
        client2.setId(4L);
        client2.setNom("Faye");
        client2.setPrenom("Mansour");
        client2.setTelephone("123-123-123a");
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(client);
        clients.add(client2);

        when(repository.findAll()).thenReturn(clients);
        assertEquals(clients,repository.findAll());
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
        Optional<Client> foundClient = repository.findById(client.getId());
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

        assertEquals(repository.save(client),client);

        verify(repository,times(1)).save(client);
    }

    @Test
    void deleteClient(){
        Client client = new Client();
        client.setId(5L);
        client.setNom("Diallo");
        client.setPrenom("Mohamed");
        client.setTelephone("123-123-123a");

        repository.deleteById(5L);

        verify(repository,times(1)).deleteById(client.getId());
    }

    //We use @MockBean to create and inject a mock for the ClientRepository (if you do not do so, the application context cannot start), and we set its expectations using Mockito.

}
