package com.esg.esg_monitor.controller;

import com.esg.esg_monitor.model.Equipamento;
import com.esg.esg_monitor.services.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
//@RequiredArgsConstructor

public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @GetMapping
    public List<Equipamento> listar() {
        return equipamentoService.listarTodos();
    }

    @PostMapping
    public Equipamento salvar(@RequestBody Equipamento equipamento) {
        return equipamentoService.salvar(equipamento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        equipamentoService.deletar(id);
    }

    @PutMapping("/{id}")
    public Equipamento atualizar(@PathVariable Long id, @RequestBody Equipamento equipamento) {
        return equipamentoService.atualizar(id, equipamento);
    }
}
