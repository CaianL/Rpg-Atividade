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
        // Validação básica dos atributos
        if (item.getNomeItem() == null || item.getNomeItem().isEmpty()) {
            throw new Errors("Erro: Nome do item não pode ser vazio");
        }

        if (item.getTipoItem() == null || item.getTipoItem().isEmpty()) {
            throw new Errors("Erro: Tipo do item não pode ser vazio");
        }

        // Verifica se os pontos de força e defesa são válidos
        try {
            item.setForcaeDefesaex(item.getForcaex(), item.getDefesaex());
        } catch (Errors e) {
            throw e;
        }

        return itensRepository.save(item);
    }

    public List<ItensModel> listarItens() {
        return itensRepository.findAll();
    }

    public Optional<ItensModel> buscarItemById(int id) {
        return itensRepository.findById(id);
    }

    public ItensModel EquiparItemPersonagem(int personagemId, int itemId) {
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(personagemId);
        Optional<ItensModel> itemOptional = itensRepository.findById(itemId);

        if (personagemOptional.isPresent() && itemOptional.isPresent()) {
            PersonagemModel personagem = personagemOptional.get();
            ItensModel item = itemOptional.get();

            if (item.getPersonagem() != null && item.getPersonagem().getId() != personagemId) {
                throw new Errors("Erro: Item equipado em outro personagem");
            }

            item.setPersonagem(personagem);
            personagem.adicionarItem(item);
            personagemRepository.save(personagem);
            return itensRepository.save(item);
        }

        throw new Errors("Erro: Personagem/Item não encontrado");
    }

    public List<ItensModel> listarItensPersonagemByID(int personagemId) {
        if (!personagemRepository.existsById(personagemId)) {
            throw new Errors("Erro: Personagem não encontrado");
        }

        return itensRepository.findByPersonagemId(personagemId);
    }

    public void removerItemPersonagem(int personagemId, int itemId) {
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(personagemId);
        Optional<ItensModel> itemOptional = itensRepository.findById(itemId);

        if (personagemOptional.isPresent() && itemOptional.isPresent()) {
            PersonagemModel personagem = personagemOptional.get();
            ItensModel item = itemOptional.get();

            if (item.getPersonagem() != null && item.getPersonagem().getId() == personagemId) {
                personagem.removerItem(item);
                item.setPersonagem(null);
                personagemRepository.save(personagem);
                itensRepository.save(item);
            } else {
                throw new Errors("Erro: Item não associado ao personagem");
            }
        } else {
            throw new Errors("Erro: Personagem/Item não encontrado");
        }
    }

    public ItensModel GetAmuletoPersonagem(int personagemId) {
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(personagemId);

        if (personagemOptional.isPresent()) {
            PersonagemModel personagem = personagemOptional.get();
            ItensModel amuleto = personagem.getAmuleto();

            if (amuleto != null) {
                return amuleto;
            } else {
                throw new Errors("Erro: Personagem não possui amuleto");
            }
        } else {
            throw new Errors("Erro: Personagem não encontrado");
        }
    }
}