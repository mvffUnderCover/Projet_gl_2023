package com.atgl.g5api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    @Test
    void GettersAndSettersTest(){
        Client client = new Client();
        client.setId(5L);
        client.setNom("Diallo");
        client.setPrenom("Mohamed");
        client.setTelephone("123-123-123a");

        assertEquals(5L,client.getId());
        assertEquals("Diallo",client.getNom());
        assertEquals("Mohamed",client.getPrenom());
        assertEquals("123-123-123a",client.getTelephone());
    }

}
