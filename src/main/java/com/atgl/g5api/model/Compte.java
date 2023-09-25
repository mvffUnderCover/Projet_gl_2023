package com.atgl.g5api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="comptes")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int solde;

    @Column(name = "Owner")
    private Long owner;

    public Compte (){
        this.solde=0;
    }
}
