package br.com.fiap.piggybank.controllers;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.piggybank.exception.RestNotFoundException;
import br.com.fiap.piggybank.models.Conta;
import br.com.fiap.piggybank.models.ContaDto;
import br.com.fiap.piggybank.models.Despesa;
import br.com.fiap.piggybank.models.RestError;
import br.com.fiap.piggybank.repository.ContaRepository;
import br.com.fiap.piggybank.repository.DespesaRepository;
import br.com.fiap.piggybank.service.ContaService;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired // IoD IoC
    ContaRepository repository;

    @Autowired
    ContaService service;

    @GetMapping
    public List<ContaDto> index(){
        log.info("buscando lista de contas");
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody @Valid Conta conta){
        log.info("cadastrando conta: " + conta);
        repository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Conta> show(@PathVariable Long id){
        log.info("buscando conta com id " + id);
        return ResponseEntity.ok(getConta(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Conta> destroy(@PathVariable Long id){
        log.info("apagando conta com id " + id);
        repository.delete(getConta(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody @Valid Conta conta){
        log.info("alterando conta com id " + id);
        getConta(id);
        conta.setId(id);
        repository.save(conta);
        return ResponseEntity.ok(conta);
    }

    private Conta getConta(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }
    
}
