package com.example.demo.Models;
import com.example.demo.Exceptions.Errors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ItensModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeItem;
    protected String tipoItem;
    private int Forcaex;
    private int Defesaex;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    @JsonIgnore
    private PersonagemModel personagem;

    public int getId() {
        return id;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public String getTipoItem() {
        return tipoItem;
    }

    public int getForcaex() {
        return Forcaex;
    }

    public int getDefesaex() {
        return Defesaex;
    }

    public PersonagemModel getPersonagem() {
        return personagem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public void setForcaeDefesaex(int Forcaex, int Defesaex) {
        if (Forcaex > 10 || Defesaex > 10 || Forcaex + Defesaex > 10)
            throw new Errors("Erro: Pontos insuficientes");
        else {
            this.Forcaex = Forcaex;
            this.Defesaex = Defesaex;
        }
    }
    public void setPersonagem(PersonagemModel personagem) {
        this.personagem = personagem;
    }
}
