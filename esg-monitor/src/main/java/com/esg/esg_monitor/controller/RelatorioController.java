package com.esg.esg_monitor.controller;

import com.esg.esg_monitor.model.Relatorio;
import com.esg.esg_monitor.services.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
//@RequiredArgsConstructor

public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping
    public List<Relatorio> listar() {
        return relatorioService.listarTodos();
    }

    @PostMapping
    public Relatorio salvar(@RequestBody Relatorio relatorio) {
        return relatorioService.salvar(relatorio);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        relatorioService.deletar(id);
    }

    @PutMapping("/{id}")
    public Relatorio atualizar(@PathVariable Long id, @RequestBody Relatorio relatorio) {
        return relatorioService.atualizar(id, relatorio);
    }
}
