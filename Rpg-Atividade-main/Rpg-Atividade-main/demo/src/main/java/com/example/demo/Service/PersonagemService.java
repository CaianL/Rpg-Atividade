package com.example.demo.Service;

import com.example.demo.Exceptions.Errors;
import com.example.demo.Models.ItensModel;
import com.example.demo.Models.PersonagemModel;
import com.example.demo.Repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public PersonagemModel cadastrarPersonagem(PersonagemModel personagem) {
        return personagemRepository.save(personagem);
    }

    public List<PersonagemModel> listarPersonagens() {
        return personagemRepository.findAll();
    }

    public Optional<PersonagemModel> buscarPersonagemPorId(int id) {
        return personagemRepository.findById(id);
    }

    public PersonagemModel MudarNomePersonagembyID(int id, String novoNome) {
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(id);

        if (personagemOptional.isPresent()) {
            PersonagemModel personagem = personagemOptional.get();
            personagem.setNomepersonagem(novoNome);
            return personagemRepository.save(personagem);
        } else {
            return null;
        }


    }

    public String removerPersonagemByID(int id) {
        personagemRepository.deleteById(id);
        return "Personagem Deletado";
    }


    public PersonagemModel atualizarAtributos(int id, int forca, int defesa) {
        Optional<PersonagemModel> personagemOptional = personagemRepository.findById(id);

        PersonagemModel personagem = null;
        if (personagemOptional.isPresent()) {
            personagem = personagemOptional.get();
            personagem.setForcaeDefesa(forca, defesa);
        } else {
            throw new Errors("Erro: Personagem não encontrado");
        }
        ;
        return personagemRepository.save(personagem);
    }
}