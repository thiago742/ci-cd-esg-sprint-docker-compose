package com.esg.esg_monitor.controller;

import com.esg.esg_monitor.model.Manutencao;
import com.esg.esg_monitor.services.ManutencaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manutencoes")
//@RequiredArgsConstructor

public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    public ManutencaoController(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    @GetMapping
    public List<Manutencao> listar() {
        return manutencaoService.listarTodas();
    }

    @PostMapping
    public Manutencao salvar(@RequestBody Manutencao manutencao) {
        return manutencaoService.salvar(manutencao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        manutencaoService.deletar(id);
    }

    @PutMapping("/{id}")
    public Manutencao atualizar(@PathVariable Long id, @RequestBody Manutencao manutencao) {
        return manutencaoService.atualizar(id, manutencao);
    }
}
