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
public class ItemControllers {

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
    public ResponseEntity<?> ListaItens(){
        List<ItensModel> itensModels = Iserv.listarItens();
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> BuscarByID(@PathVariable int Id){
      Optional<ItensModel> item = Iserv.buscarItemById(Id);
      if(item.isPresent())
          return Responses.Sucesso("Item Encontrado!\n"+ item);
      else{
          return Responses.Falha("Item Não Encontrado",HttpStatus.BAD_REQUEST);
      }
    }
}



