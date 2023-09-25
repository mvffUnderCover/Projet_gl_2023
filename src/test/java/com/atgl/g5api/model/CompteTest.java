package com.atgl.g5api.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompteTest {

    @Test
    void ConstructorTest(){
        assertEquals(0,new Compte().getSolde());
    }
    @Test
    void GettersAndSettersTest(){
        Compte compte = new Compte();
        compte.setId(1L);
        compte.setOwner(1L);

        assertEquals(1L,compte.getId());
        assertEquals(1L,compte.getOwner());
        assertEquals(0,compte.getSolde());
    }
}
