package com.atgl.g5api.service;

import com.atgl.g5api.model.Compte;
import com.atgl.g5api.repository.CompteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CompteServiceTest {

    @Mock
    private CompteRepository repository;

    @InjectMocks
    private CompteService service;

    @Test
    void crediterCompteTest(){
        Compte compte = new Compte();
        compte.setOwner(1L);
        compte.setId(1L);

        Compte c = new Compte();
        c.setId(1L);
        c.setSolde(2000);
        c.setOwner(1L);

        when(repository.save(compte)).thenReturn(compte);
        when(repository.findByOwner(1L)).thenReturn(compte);
        service.crediterCompte(1,2000);
        verify(repository,times(1)).findByOwner(compte.getOwner());
        verify(repository,times(1)).save(compte);
        assertEquals(repository.save(compte),c);
    }

    @Test
    void getCompteTest(){
        Compte compte = new Compte();
        compte.setOwner(1L);
        compte.setId(1L);
        when(repository.findByOwner(1L)).thenReturn(compte);
        assertEquals(Optional.of(compte),service.getCompte(1));
        verify(repository,times(1)).findByOwner(1L);
    }

    @Test
    void createCompteTest(){
        Compte compte = new Compte();
        compte.setOwner(1L);
        compte.setId(2L);

        when(repository.count()).thenReturn(1L);
        when(repository.save(compte)).thenReturn(compte);

        Compte c = service.createCompte(1L);
        assertEquals(compte,c);
        verify(repository,times(1)).save(compte);
    }

    @Test
    void deleteAccountTest(){
        Compte compte = new Compte();
        compte.setOwner(1L);
        compte.setId(2L);

        repository.delete(compte);

        verify(repository,times(1)).delete(compte);
    }
}
