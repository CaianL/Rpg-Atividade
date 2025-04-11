package com.example.demo.Controllers;

import com.example.demo.Exceptions.Responses;
import com.example.demo.Exceptions.Errors;
import com.example.demo.Models.ItensModel;
import com.example.demo.Models.PersonagemModel;
import com.example.demo.Repository.ItemRepository;
import com.example.demo.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens")
public class ItensController {

    @Autowired
    private ItemService Iserv;

    @PostMapping
    public ResponseEntity<?> CadastroItem(@RequestBody ItensModel Item) {
        try {
            ItensModel novoItem = Iserv.cadastrarItem(Item);
            return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
        } catch (Errors e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> ListaItens() {
        List<ItensModel> itensModels = Iserv.listarItens();
        return Responses.Sucesso("Itens encontrados", itensModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> BuscarByID(@PathVariable int id) {
        Optional<ItensModel> item = Iserv.buscarItemById(id);
        if (item.isPresent())
            return Responses.Sucesso("Item Encontrado!", item.get());
        else {
            return Responses.Falha("Item Não Encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/personagem/{personagemId}")
    public ResponseEntity<?> ListarItensPersonagem(@PathVariable int personagemId) {
        try {
            List<ItensModel> itens = Iserv.listarItensPersonagemByID(personagemId);
            return Responses.Sucesso("Itens do personagem encontrados", itens);
        } catch (Errors e) {
            return Responses.Falha(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/equipar/{personagemId}/{itemId}")
    public ResponseEntity<?> EquiparItemPersonagem(@PathVariable int personagemId, @PathVariable int itemId) {
        try {
            ItensModel item = Iserv.EquiparItemPersonagem(personagemId, itemId);
            return Responses.Sucesso("Item equipado com sucesso", item);
        } catch (Errors e) {
            return Responses.Falha(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remover/{personagemId}/{itemId}")
    public ResponseEntity<?> RemoverItemPersonagem(@PathVariable int personagemId, @PathVariable int itemId) {
        try {
            Iserv.removerItemPersonagem(personagemId, itemId);
            return Responses.Sucesso("Item removido do personagem com sucesso", null);
        } catch (Errors e) {
            return Responses.Falha(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/amuleto/{personagemId}")
    public ResponseEntity<?> BuscarAmuletoPersonagem(@PathVariable int personagemId) {
        try {
            ItensModel amuleto = Iserv.GetAmuletoPersonagem(personagemId);
            return Responses.Sucesso("Amuleto encontrado", amuleto);
        } catch (Errors e) {
            return Responses.Falha(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
