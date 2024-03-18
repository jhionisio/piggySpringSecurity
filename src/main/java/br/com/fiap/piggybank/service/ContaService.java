package br.com.fiap.piggybank.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.piggybank.models.ContaDto;
import br.com.fiap.piggybank.models.Despesa;
import br.com.fiap.piggybank.repository.ContaRepository;
import br.com.fiap.piggybank.repository.DespesaRepository;

@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    DespesaRepository despesaRepository;

    public List<ContaDto> findAll() {
        // buscar todas as contas
        var contas = contaRepository.findAll();
        List<ContaDto> dtos = new ArrayList<>();

        // para cada conta
        contas.forEach(conta -> {
            //pegar todas as despesas dessa conta
            var despesas = despesaRepository.findByContaId(conta.getId());

            var total = despesas
                .stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            dtos.add(new ContaDto(conta, conta.getSaldoInicial().subtract(total)));

        });

        return dtos;
    }


    
}
