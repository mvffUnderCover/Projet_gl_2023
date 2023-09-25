package com.atgl.g5api.repository;

import com.atgl.g5api.model.Compte;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CompteRepositoryTest {

    @MockBean
    private CompteRepository repository;
    @Test
    void findByOwnerTest(){
        Compte compte = new Compte();
        compte.setId(1L);
        compte.setOwner(1L);

        when(repository.findByOwner(compte.getOwner())).thenReturn(compte);
        assertEquals(compte,repository.findByOwner(compte.getOwner()));
    }

    @Test
    void saveCompteTest(){
        Compte compte = new Compte();
        compte.setId(1L);
        compte.setOwner(1L);

        when(repository.save(compte)).thenReturn(compte);
        assertEquals(compte,repository.save(compte));
        verify(repository,times(1)).save(compte);
    }
}
