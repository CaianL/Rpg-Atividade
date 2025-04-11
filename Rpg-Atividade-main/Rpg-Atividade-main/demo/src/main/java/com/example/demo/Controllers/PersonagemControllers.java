package com.example.demo.Controllers;

import com.example.demo.Exceptions.Errors;
import com.example.demo.Models.PersonagemModel;
import com.example.demo.Service.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personagens")
public class PersonagemControllers {

        @Autowired
        private PersonagemService persService;


        @PostMapping
        public ResponseEntity<?> cadastrarPersonagem(@RequestBody PersonagemModel personagem) {
            try {
                PersonagemModel novoPersonagem = persService.cadastrarPersonagem(personagem);
                return new ResponseEntity<>(novoPersonagem, HttpStatus.CREATED);
            } catch (Errors e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        @GetMapping
        public ResponseEntity<List<PersonagemModel>> listarPersonagens() {
            List<PersonagemModel> personagens = persService.listarPersonagens();
            return new ResponseEntity<>(personagens, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<PersonagemModel> buscarPersonagemPorId(@PathVariable int id) {
            Optional<PersonagemModel> personagem = persService.buscarPersonagemPorId(id);

            if (personagem.isPresent()) {
                return new ResponseEntity<>(personagem.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping("/{id}/nome")
        public ResponseEntity<PersonagemModel> atualizarNomePersonagem(@PathVariable int id, @RequestParam String nome) {
            PersonagemModel personagemAtualizado = persService.MudarNomePersonagembyID(id, nome);

            if (personagemAtualizado != null) {
                return new ResponseEntity<>(personagemAtualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping("/{id}/atributos")
        public ResponseEntity<?> atualizarAtributos(@PathVariable int id,
                                                    @RequestParam int forca,
                                                    @RequestParam int defesa) {
            try {
                PersonagemModel personagemAtualizado = persService.atualizarAtributos(id, forca, defesa);

                if (personagemAtualizado != null) {
                    return new ResponseEntity<>(personagemAtualizado, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Errors e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> removerPersonagem(@PathVariable int id) {
            Optional<PersonagemModel> personagem = persService.buscarPersonagemPorId(id);

            if (personagem.isPresent()) {
                persService.removerPersonagemByID(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // Handler para exceções de validação
        @ExceptionHandler(Errors.class)
        public ResponseEntity<String> handleValidationException(Errors e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
