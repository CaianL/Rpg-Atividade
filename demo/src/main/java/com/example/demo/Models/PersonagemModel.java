package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.lang.reflect.Array;

public class PersonagemModel {
    @Entity
    @Id
    int id;
    String nomepersonagem;
    String classepersonagem;
    Array atributos{
        int Força;
        int Defesa;
    };

    public int getId() {
        return id;
    }
    public String getNomepersonagem(){
        return nomepersonagem;
    }

    public String getClassepersonagem() {
        return classepersonagem;
    }

    public Array getAtributosForca() {
        return atributos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAtributos(Array atributos) {
        this.atributos = atributos;
    }

    public void setClassepersonagem(String classepersonagem) {
        this.classepersonagem = classepersonagem;
    }
}
