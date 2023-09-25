package com.atgl.g5api.controller;

import com.atgl.g5api.model.Client;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {
    //Pour simuler des requetes
    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetClients() throws Exception {
        mockMvc.perform(get("http://localhost:9001/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].prenom", Matchers.equalTo("Mohamed")));
    }
    @Test
    void testGetClientsById() throws Exception {
        mockMvc.perform(get("http://localhost:9001/clients/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['prenom']", Matchers.equalTo("Makhtar")));
    }

    @Test
    void getClientAccountTest() throws Exception {
        mockMvc.perform(get("http://localhost:9001/clients/3/compte"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['owner']", Matchers.equalTo(3)))
                .andExpect(jsonPath("$['solde']", Matchers.equalTo(40020400)));
    }

    @Test
    void createClientAccountTest() throws Exception
    {
        Client client = new Client();
        client.setId(12L);
        client.setTelephone("xx-xxx-xx-xx");
        client.setNom("TestPost");
        client.setPrenom("Test");
        mockMvc.perform( MockMvcRequestBuilders
                        .post("/clients")
                        .content(asJsonString(client))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void deleteClientTest() throws Exception
    {
        // Obtenez la liste actuelle des clients de l'API
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/clients"))
                .andExpect(status().isOk())
                .andReturn();

        // Récupérez le contenu de la réponse, qui devrait être une liste de clients
        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Client> clientList = objectMapper.readValue(responseBody, new TypeReference<List<Client>>() {});

        // Vérifiez s'il y a au moins un client dans la liste
        if (!clientList.isEmpty()) {
            // Identifiez le dernier client dans la liste
            Client lastClient = clientList.get(clientList.size() - 1);

            // Utilisez l'ID du dernier client pour effectuer une requête DELETE pour le supprimer
            mockMvc.perform(MockMvcRequestBuilders.delete("/clients/{id}", lastClient.getId()))
                    .andExpect(status().isOk());
        } else {
            // Gérer le cas où il n'y a pas de clients dans la liste
            // Vous pouvez lever une exception, afficher un message, ou effectuer toute autre action appropriée.
            // Dans cet exemple, nous lançons une exception.
            throw new RuntimeException("La liste des clients est vide.");
        }
    }
}
