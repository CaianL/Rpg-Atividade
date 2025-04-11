package com.example.demo.Models;
import com.example.demo.Exceptions.Errors;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class PersonagemModel {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    private String Nomepersonagem;
    private String NomeAventureiro;
    private String classepersonagem;
    private int Forca;
    private int Defesa;
    private int Levelpersonagem;

    @OneToMany(mappedBy = "personagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensModel> itens = new ArrayList<>();

    public int getId() {
        return id;
    }
    public String getNomepersonagem(){
        return Nomepersonagem;
    }

    public String getClassepersonagem() {
        return classepersonagem;
    }

    public int getForca() {
        return Forca;
    }

    public int getDefesa() {
        return Defesa;
    }

    public int getLevelpersonagem() {
        return Levelpersonagem;
    }

    public String getNomeAventureiro() {
        return NomeAventureiro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setForcaeDefesa(int Força, int Defesa) {
        if (Força > 10 || Defesa > 10 || (Força + Defesa) > 10)
            throw new Errors("Erro: Pontos Insuficientes");
        else {
            this.Forca = Forca;
            this.Defesa = Defesa;
        }
    }
    public void setClassepersonagem(String classepersonagem) {
        this.classepersonagem = classepersonagem;
    }

    public void setNomepersonagem(String nomepersonagem) {
        Nomepersonagem = nomepersonagem;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        NomeAventureiro = nomeAventureiro;
    }


    public void setLevelpersonagem(int levelpersonagem) {
        Levelpersonagem = levelpersonagem;
    }

    public List<ItensModel> getItens() {
        return itens;
    }

    public void setItens(List<ItensModel> itens) {
        this.itens = itens;
    }

    // Método para adicionar um item
    public void adicionarItem(ItensModel item) {
        itens.add(item);
        setItens(this.itens);
    }

    // Método para remover um item
    public void removerItem(ItensModel item) {
        itens.remove(item);
        item.setPersonagem(null);
    }


    public int getForcaTotal() {
        int bonusTotal = 0;
        for (ItensModel item : itens) {
            bonusTotal += item.getForcaex();
        }
        return Forca + bonusTotal;
    }

    public int getDefesaTotal() {
        int bonusTotal = 0;
        for (ItensModel item : itens) {
            bonusTotal += item.getDefesaex();
        }
        return Defesa + bonusTotal;
    }

    public ItensModel getAmuleto() {
        for (ItensModel item : itens) {
            if (Objects.equals(item.tipoItem, "Amuleto")) {
                return item;
            }
        }
        return null;
    }
}


