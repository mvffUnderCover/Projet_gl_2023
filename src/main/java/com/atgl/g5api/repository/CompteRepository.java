package com.atgl.g5api.repository;

import com.atgl.g5api.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Long> {

    Compte findByOwner(long owner);

    void deleteByOwner(long owner);
}
