package com.example.demo.Service;

import com.example.demo.Exceptions.Errors;
import com.example.demo.Models.ItensModel;
import com.example.demo.Models.PersonagemModel;
import com.example.demo.Repository.ItemRepository;
import com.example.demo.Repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itensRepository;

    @Autowired
    private PersonagemRepository personagemRepository;
    public ItensModel cadastrarItem(ItensModel item) {
        // Validação básica
        if (item.getForcaex() < 0 || item.getDefesaex() < 0) {
            throw new Errors("Os bônus de força e defesa não podem ser negativos");
        }

        return itensRepository.save(item);
    }

    // Listar todos os itens
    public List<ItensModel> listarItens() {
        return itensRepository.findAll();
    }

    // Buscar item por ID
    public Optional<ItensModel> buscarItemPorId(int id) {
        return itensRepository.findById(id);
    }

    // Adicionar item ao personagem
    public ItensModel adicionarItemAoPersonagem(int personagemId, int itemId) {
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(personagemId);
        Optional<ItensModel> itemOptional = itensRepository.findById(itemId);

        if (personagemOptional.isPresent() && itemOptional.isPresent()) {
            PersonagemModel personagem = personagemOptional.get();
            ItensModel item = itemOptional.get();

            // Verificar se o item já está associado a outro personagem
            if (item.getPersonagem() != null && item.getPersonagem().getId() != personagemId) {
                throw new PersonagemValidationException("Este item já está associado a outro personagem");
            }

            personagem.adicionarItem(item);
            return itensRepository.save(item);
        }

        throw new PersonagemValidationException("Personagem ou Item não encontrado");
    }

    // Listar itens por personagem
    public List<ItensModel> listarItensPorPersonagem(int personagemId) {
        if (!personagemRepository.existsById(personagemId)) {
            throw new PersonagemValidationException("Personagem não encontrado com ID: " + personagemId);
        }

        return itensRepository.findByPersonagemId(personagemId);
    }

    // Remover item do personagem
    public void removerItemDoPersonagem(int personagemId, int itemId) {
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(personagemId);
        Optional<ItensModel> itemOptional = itensRepository.findById(itemId);

        if (personagemOptional.isPresent() && itemOptional.isPresent()) {
            PersonagemModel personagem = personagemOptional.get();
            ItensModel item = itemOptional.get();

            // Verificar se o item está associado ao personagem
            if (item.getPersonagem() != null && item.getPersonagem().getId() == personagemId) {
                personagem.removerItem(item);
                item.setPersonagem(null);
                itensRepository.save(item);
            } else {
                throw new PersonagemValidationException("Este item não está associado a este personagem");
            }
        } else {
            throw new PersonagemValidationException("Personagem ou Item não encontrado");
        }
    }

    // Buscar amuleto do personagem
    public ItensModel buscarAmuletoDoPersonagem(int personagemId) {
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(personagemId);

        if (personagemOptional.isPresent()) {
            PersonagemModel personagem = personagemOptional.get();
            ItensModel amuleto = personagem.getAmuleto();

            if (amuleto != null) {
                return amuleto;
            } else {
                throw new Errors("Este personagem não possui um amuleto");
            }
        }

        throw new Errors("Personagem não encontrado com ID: " + personagemId);
    }
}